package com.tinqinacademy.bff.api.base;


import com.tinqinacademy.bff.api.errors.Errors;
import io.vavr.control.Either;

public interface OperationProcessor<O extends OperationOutput, I extends OperationInput> {
    Either<Errors, O> process(I input);
}
