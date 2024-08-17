package com.tinqinacademy.bff.core.exceptions.comments;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NoCommentsFoundException extends BaseException {
    public NoCommentsFoundException( String id) {
        super(String.format("No comments Found for room with id: %s", id), HttpStatus.NOT_FOUND);
    }
}
