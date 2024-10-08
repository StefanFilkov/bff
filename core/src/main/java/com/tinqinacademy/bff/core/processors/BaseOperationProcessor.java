package com.tinqinacademy.bff.core.processors;

import com.tinqinacademy.bff.api.base.OperationInput;
import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.errors.OperationError;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.domain.HotelClient;
import io.vavr.control.Either;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.util.Set;

public abstract class BaseOperationProcessor {
    protected final ConversionService conversionService;
    protected final Validator validator;
    protected final ErrorMapper errorMapper;
    protected final HotelClient hotelClient;
    protected final ObjectMapperConvertor objectMapperConvertor;

    protected BaseOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        this.conversionService = conversionService;
        this.validator = validator;
        this.errorMapper = errorMapper;
        this.hotelClient = hotelClient;
        this.objectMapperConvertor = objectMapperConvertor;
    }


    protected Either<Errors, ? extends OperationInput> validateInput(OperationInput input) {
        Set<ConstraintViolation<OperationInput>> violations = validator.validate(input);

        if (violations.isEmpty()) {
            return Either.right(input);
        }

        Errors resultingErrors = Errors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .build();

        violations.forEach(violation -> {
            OperationError build = OperationError
                    .builder()
                    .message(violation.getPropertyPath() + violation.getMessage())
                    .status(resultingErrors.getStatus())
                    .build();
            resultingErrors.addError(build);
        });

        return Either.left(resultingErrors);

    }
}
