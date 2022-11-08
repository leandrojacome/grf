package br.com.poupex.investimento.recursosfinanceiros.infrastructure.feign.retry;

import feign.RetryableException;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientFeignRetryer implements Retryer {

    private final int retryMaxAttempts;
    private final long retryInterval;

    private int attempt = 1;

    public ClientFeignRetryer() {
        this(2, SECONDS.toMillis(1));
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        log.info("Feign retry attempt {} due to {} ", attempt, e.getMessage());
        if (attempt++ == retryMaxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(retryInterval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new ClientFeignRetryer(retryMaxAttempts, retryInterval);
    }

}
