package com.tinqinacademy.bff.core.exceptions.hotel;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NoBedsInInputException extends BaseException {
    public NoBedsInInputException() {
        super("No beds in input", HttpStatus.BAD_REQUEST);
    }
}
