package br.com.poupex.investimento.recursosfinanceiros.infrastructure.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Arrays;

/**
 * https://github.com/spring-cloud/spring-cloud-security/blob/master/spring-cloud-security/src/main/java/org/springframework/cloud/security/oauth2/client/feign/OAuth2FeignRequestInterceptor.java
 */
@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    public static final String BEARER = "Bearer";

    public static final String AUTHORIZATION = "Authorization";

    private final OAuth2ClientContext oAuth2ClientContext;

    private final OAuth2ProtectedResourceDetails resource;

    private final String tokenType;

    private final String header;

    private final List<String> exclusions = List.of("/dados/serie/bcdata.sgs");

    private AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(
            Arrays.<AccessTokenProvider>asList(new AuthorizationCodeAccessTokenProvider(),
                    new ImplicitAccessTokenProvider(),
                    new ResourceOwnerPasswordAccessTokenProvider(),
                    new ClientCredentialsAccessTokenProvider()));

    public OAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
                                         OAuth2ProtectedResourceDetails resource) {
        this(oAuth2ClientContext, resource, BEARER, AUTHORIZATION);
    }

    public OAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
                                         OAuth2ProtectedResourceDetails resource, String tokenType, String header) {
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.resource = resource;
        this.tokenType = tokenType;
        this.header = header;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (exclusions.stream().noneMatch(exclusion -> template.url().startsWith(exclusion))) {
            template.header(header); // Clears out the header, no "clear" method available.
            template.header(header, extract(tokenType));
        }
    }

    protected String extract(String tokenType) {
        OAuth2AccessToken accessToken = getToken();
        return String.format("%s %s", tokenType, accessToken.getValue());
    }

    public OAuth2AccessToken getToken() {

        OAuth2AccessToken accessToken = oAuth2ClientContext.getAccessToken();
        if (accessToken == null || accessToken.isExpired()) {
            try {
                accessToken = acquireAccessToken();
            }
            catch (UserRedirectRequiredException e) {
                oAuth2ClientContext.setAccessToken(null);
                String stateKey = e.getStateKey();
                if (stateKey != null) {
                    Object stateToPreserve = e.getStateToPreserve();
                    if (stateToPreserve == null) {
                        stateToPreserve = "NONE";
                    }
                    oAuth2ClientContext.setPreservedState(stateKey, stateToPreserve);
                }
                throw e;
            }
        }
        return accessToken;
    }

    protected OAuth2AccessToken acquireAccessToken() {
        AccessTokenRequest tokenRequest = oAuth2ClientContext.getAccessTokenRequest();
        if (tokenRequest == null) {
            throw new AccessTokenRequiredException(
                    "Cannot find valid context on request for resource '"
                            + resource.getId() + "'.",
                    resource);
        }
        String stateKey = tokenRequest.getStateKey();
        if (stateKey != null) {
            tokenRequest.setPreservedState(
                    oAuth2ClientContext.removePreservedState(stateKey));
        }
        OAuth2AccessToken existingToken = oAuth2ClientContext.getAccessToken();
        if (existingToken != null) {
            oAuth2ClientContext.setAccessToken(existingToken);
        }
        OAuth2AccessToken obtainableAccessToken;
        obtainableAccessToken = accessTokenProvider.obtainAccessToken(resource,
                tokenRequest);
        if (obtainableAccessToken == null || obtainableAccessToken.getValue() == null) {
            throw new IllegalStateException(
                    " Access token provider returned a null token, which is illegal according to the contract.");
        }
        oAuth2ClientContext.setAccessToken(obtainableAccessToken);
        return obtainableAccessToken;
    }

    public void setAccessTokenProvider(AccessTokenProvider accessTokenProvider) {
        this.accessTokenProvider = accessTokenProvider;
    }
}
