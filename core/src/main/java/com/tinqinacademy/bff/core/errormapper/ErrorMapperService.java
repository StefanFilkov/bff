package com.tinqinacademy.bff.core.errormapper;



import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.errors.OperationError;
import com.tinqinacademy.bff.core.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ErrorMapperService<E extends OperationError> implements ErrorMapper {


    @Override
    public Errors mapErrors(Throwable throwable) {
        List<OperationError> OpErrors = new ArrayList<>();


        if (throwable instanceof BaseException) {

            BaseException baseException = (BaseException) throwable;
            OpErrors.add(OperationError
                    .builder()
                    .message(throwable.getMessage())
                    .status(baseException.getCode())
                    .build());

            Errors result = Errors
                    .builder()
                    .errors(OpErrors)
                    .status(baseException.getCode())
                    .build();
            return result;
        }


        OpErrors.add(OperationError
                .builder()
                .message(throwable.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build());

        Errors result = Errors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .errors(OpErrors)
                .build();


        return result;
    }


}
