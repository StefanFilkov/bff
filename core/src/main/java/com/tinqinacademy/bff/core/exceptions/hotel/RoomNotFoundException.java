package com.tinqinacademy.bff.core.exceptions.hotel;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends BaseException {
    public RoomNotFoundException(String id) {
        super(String.format("Room with id: %s not found", id), HttpStatus.NOT_FOUND);
    }
}
