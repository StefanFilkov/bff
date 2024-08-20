package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomInputBFF;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOperation;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentInputBFF;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentOutputBFF;
import com.tinqinacademy.bff.api.operations.comments.putcomment.PutCommentInputBFF;
import com.tinqinacademy.bff.api.operations.comments.putcomment.PutCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.putcomment.PutCommentOutputBFF;
import com.tinqinacademy.bff.api.operations.comments.updatecomment.UpdateCommentInputBFF;
import com.tinqinacademy.bff.api.operations.comments.updatecomment.UpdateCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.updatecomment.UpdateCommentOutputBFF;
import com.tinqinacademy.bff.api.urls.URLMappingsComments;
import com.tinqinacademy.comments.api.URLMappings;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomInput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOutput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentInput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentOutput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentInput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentOutput;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController extends BaseController {

    private final GetCommentsByRoomOperation getCommentsByRoomOperation;
    private final PostCommentOperation postCommentOperation;
    private final UpdateCommentOperation updateCommentOperation;
    private final PutCommentOperation putCommentOperation;

    public CommentsController(GetCommentsByRoomOperation getCommentsByRoomOperation, PostCommentOperation postCommentOperation, UpdateCommentOperation updateCommentOperation, PutCommentOperation putCommentOperation) {
        this.getCommentsByRoomOperation = getCommentsByRoomOperation;
        this.postCommentOperation = postCommentOperation;
        this.updateCommentOperation = updateCommentOperation;
        this.putCommentOperation = putCommentOperation;
    }

    @GetMapping(URLMappingsComments.GET_COMMENTS_BY_ROOM_ID)
    ResponseEntity<?> getCommentsByRoom(@PathVariable String roomId) {
        GetCommentsByRoomInputBFF input = GetCommentsByRoomInputBFF.builder()
                .id(roomId)
                .build();
        Either<Errors, GetCommentsByRoomOutputBFF> result = getCommentsByRoomOperation.process(input);
        return handleResult(result);
    }

    @PostMapping(URLMappingsComments.POST_COMMENT)
    ResponseEntity<?> postComment(@PathVariable String roomId, @RequestBody PostCommentInputBFF input) {
        input.setRoomId(roomId);
        Either<Errors, PostCommentOutputBFF> result = postCommentOperation.process(input);
        return handleResult(result);
    }

    @PatchMapping(URLMappingsComments.PATCH_COMMENT)
    ResponseEntity<?> patchComment(@PathVariable String commentId, @RequestBody UpdateCommentInputBFF input) {

        input.setId(commentId);
        Either<Errors, UpdateCommentOutputBFF> result = updateCommentOperation.process(input);
        return handleResult(result);
    }

    @PutMapping(URLMappingsComments.PUT_COMMENT)
    public ResponseEntity<?> putComment(@RequestBody @Valid PutCommentInputBFF input, @PathVariable String commentId) {
        input.setId(commentId);

        Either<Errors, PutCommentOutputBFF> result = putCommentOperation.process(input);
        return handleResult(result);

    }


}
