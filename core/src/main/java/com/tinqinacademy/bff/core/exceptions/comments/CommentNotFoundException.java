package com.tinqinacademy.bff.core.exceptions.comments;

import com.tinqinacademy.bff.core.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends BaseException {
    public CommentNotFoundException(String id) {
        super(String.format("Comment with id: %s not found", id), HttpStatus.NOT_FOUND);
    }
}