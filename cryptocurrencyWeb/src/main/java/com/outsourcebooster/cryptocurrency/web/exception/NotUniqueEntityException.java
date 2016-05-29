package com.outsourcebooster.cryptocurrency.web.exception;

/**
 * Created by rklimemnko on 30.05.2016.
 */
public class NotUniqueEntityException extends RuntimeException{

    public NotUniqueEntityException(String message) {
        super(message);
    }
    public NotUniqueEntityException(Exception e) {
        super(e);
    }

}
