package com.tinqinacademy.bff.api.errors;

import org.springframework.http.HttpStatus;

import java.util.List;

public interface ErrorsWrapper {

    void addError(OperationError error);
    List<OperationError> getErrors();
    HttpStatus getStatus();
}
