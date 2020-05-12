package com.movedtoatlanta.queryapi.controllers.exceptions;

public class NoRecordsFoundException extends Exception {
    public NoRecordsFoundException(String s, NullPointerException e) {
        super(s,e);
    }
}
