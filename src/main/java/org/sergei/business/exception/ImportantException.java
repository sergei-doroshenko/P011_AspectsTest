package org.sergei.business.exception;

public class ImportantException extends BusinessException {
    public ImportantException() {
    }

    public ImportantException(String message) {
        super(message);
    }

    public ImportantException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImportantException(Throwable cause) {
        super(cause);
    }
}
