package com.masai.exception;

public class EmailSendingException extends RuntimeException {

    public EmailSendingException(String message, MessagingException e) {
        super(message);
    }

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
