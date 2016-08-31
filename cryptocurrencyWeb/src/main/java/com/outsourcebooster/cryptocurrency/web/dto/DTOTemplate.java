package com.outsourcebooster.cryptocurrency.web.dto;

/**
 * Created by rklimemnko on 30.05.2016.
 */
public class DTOTemplate<T> {

    private String message;
    private T entity;

    public DTOTemplate(String message, T entity) {
        this.message = message;
        this.entity = entity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
