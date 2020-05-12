package com.movedtoatlanta.network.models;

import java.io.Serializable;

public class ErrorEntity implements Serializable {
    private String errorMessage;
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
