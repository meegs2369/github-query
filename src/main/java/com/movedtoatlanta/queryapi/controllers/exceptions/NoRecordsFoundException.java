package com.movedtoatlanta.queryapi.controllers.exceptions;

/**
 * Exception that is thrown when the api does not have a record.
 */
public class NoRecordsFoundException extends Exception {
    public NoRecordsFoundException(String s, NullPointerException e) {
        super(s, e);
    }
}
