package com.movedtoatlanta.network.models;

import java.io.Serializable;

/**
 * A wrapper POJO for serializing Errors. This is necessary for the spring-boot application to
 * provide a String message as JSON.
 */
public class ErrorEntity implements Serializable {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
