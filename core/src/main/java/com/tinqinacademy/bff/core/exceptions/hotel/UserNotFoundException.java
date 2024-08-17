package com.tinqinacademy.bff.core.exceptions.hotel;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String id) {
        super(String.format("User with id: %s not found", id), HttpStatus.NOT_FOUND);
    }
}
