package br.com.poupex.investimento.recursosfinanceiros.infrastructure.exception;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ExceptionMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        if (response.status() > 0) {
        	return new GifRequestException(message.getDescription() != null ? message.getDescription() : "ERRO no GIF");
        } else {
        	return errorDecoder.decode(methodKey, response);
        }
    }
}