package com.movedtoatlanta.queryapi.controllers.exceptions;

import com.movedtoatlanta.network.models.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Class will handle exceptions thrown during processing.
 */
@ControllerAdvice
public class QueryApiAdvice {

    @ExceptionHandler(NoRecordsFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorEntity handleNotFound(Exception e) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorMessage(e.getMessage());
        return errorEntity;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorEntity handleException(Exception e) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorMessage(e.getMessage());
        return errorEntity;
    }
}
