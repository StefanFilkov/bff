package com.tinqinacademy.bff.core.exceptions.hotel;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidBathroomTypeException extends BaseException {
    public InvalidBathroomTypeException(String type){
        super(String.format("Invalid bathroom type: %s", type), HttpStatus.BAD_REQUEST);
    }
}
