package br.com.poupex.investimento.recursosfinanceiros.infrastructure.exception;

public class GifRequestException extends Exception {

    public GifRequestException() {
    }

    public GifRequestException(String message) {
        super(message);
    }

    public GifRequestException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "GifRequestException: " + getMessage();
    }

}
