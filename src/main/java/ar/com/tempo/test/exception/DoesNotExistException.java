package ar.com.tempo.test.exception;

public class DoesNotExistException extends RuntimeException {

    public DoesNotExistException() {
        super();
    }

    public DoesNotExistException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoesNotExistException(String message) {
        super(message);
    }

    public DoesNotExistException(Throwable cause) {
        super(cause);
    }
}
