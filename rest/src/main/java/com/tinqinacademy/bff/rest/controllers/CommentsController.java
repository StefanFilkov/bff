package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomInputBFF;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOperation;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOutputBFF;
import com.tinqinacademy.bff.api.urls.URLMappingsComments;
import com.tinqinacademy.comments.api.URLMappings;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomInput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOutput;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController extends BaseController {

    private final GetCommentsByRoomOperation getCommentsByRoomOperation;

    public CommentsController(GetCommentsByRoomOperation getCommentsByRoomOperation) {
        this.getCommentsByRoomOperation = getCommentsByRoomOperation;
    }

    @GetMapping(URLMappingsComments.GET_COMMENTS_BY_ROOM_ID)
    ResponseEntity<?> getCommentsByRoom(@PathVariable String roomId) {
        GetCommentsByRoomInputBFF input = GetCommentsByRoomInputBFF.builder()
                .id(roomId)
                .build();
        Either<Errors, GetCommentsByRoomOutputBFF> result = getCommentsByRoomOperation.process(input);
        return handleResult(result);
    }

}
