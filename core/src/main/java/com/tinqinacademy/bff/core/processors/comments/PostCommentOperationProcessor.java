package com.tinqinacademy.bff.core.processors.comments;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentInputBFF;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomInput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOutput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentInput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentOutput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Service
@Slf4j
public class PostCommentOperationProcessor extends BaseOperationProcessor implements PostCommentOperation {
    private final CommentsClient commentsClient;

    protected PostCommentOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor, CommentsClient commentsClient) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
        this.commentsClient = commentsClient;
    }

    @Override
    public Either<Errors, PostCommentOutputBFF> process(PostCommentInputBFF input) {
        return validateInput(input).flatMap(validation -> postComment(input));
    }

    private Either<Errors, PostCommentOutputBFF> postComment(PostCommentInputBFF input) {
        return Try.of(() -> {
                    log.info("Start of postComment in bff with input {}", input);

                    PostCommentInput commentsInput = objectMapperConvertor.convert(input, PostCommentInput.class);
                    ResponseEntity<PostCommentOutput> response = commentsClient.postComment(input.getRoomId(), commentsInput);

                    PostCommentOutput responseResult = response.getBody();
                    PostCommentOutputBFF result = objectMapperConvertor.convert(responseResult, PostCommentOutputBFF.class);

                    log.info("End of postComment in bff with result {}", result);
                    return result;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
