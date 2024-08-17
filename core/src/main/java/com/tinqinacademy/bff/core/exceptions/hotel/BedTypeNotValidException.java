package com.tinqinacademy.bff.core.exceptions.hotel;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class BedTypeNotValidException extends BaseException {
    public BedTypeNotValidException(String type) {
        super(String.format("Invalid bed with type: %s", type), HttpStatus.BAD_REQUEST);
    }
}
