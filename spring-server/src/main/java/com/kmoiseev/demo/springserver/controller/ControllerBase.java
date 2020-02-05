package com.kmoiseev.demo.springserver.controller;

import com.kmoiseev.demo.springserver.exception.ModelNotFoundException;
import com.kmoiseev.demo.springserver.exception.ModelValidationException;
import com.kmoiseev.demo.springserver.view.output.ErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class ControllerBase {
    @ExceptionHandler(value = ModelValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorView handleModelValidationException(ModelValidationException e) {
        return new ErrorView(e.getMessage());
    }

    @ExceptionHandler(value = ModelNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorView handleModelNotFoundException(ModelNotFoundException e) {
        return new ErrorView(e.getMessage());
    }
}
