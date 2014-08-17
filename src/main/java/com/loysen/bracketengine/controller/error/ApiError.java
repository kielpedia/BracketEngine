package com.loysen.bracketengine.controller.error;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by kielpedia on 8/16/14.
 */
public class ApiError {
    private final List<ObjectError> errors;

    public ApiError(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
