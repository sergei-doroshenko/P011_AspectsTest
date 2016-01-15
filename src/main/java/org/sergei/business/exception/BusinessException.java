package org.sergei.business.exception;

/**
 * Created by sergei on 1/15/16.
 */
public class BusinessException extends RuntimeException {

    /**
     * Constructs new exception with null as its detail message
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message - the detail message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * @param cause - the cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause
     * @param message - the message
     * @param cause - the cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
