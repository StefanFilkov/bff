package com.tinqinacademy.bff.core.processors.comments;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentOutputBFF;
import com.tinqinacademy.bff.api.operations.comments.updatecomment.UpdateCommentInputBFF;
import com.tinqinacademy.bff.api.operations.comments.updatecomment.UpdateCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.updatecomment.UpdateCommentOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentInput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentOutput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOutput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Slf4j
@Service
public class UpdateContentOperationProcessor extends BaseOperationProcessor implements UpdateCommentOperation {
    private final CommentsClient commentsClient;

    protected UpdateContentOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor, CommentsClient commentsClient) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
        this.commentsClient = commentsClient;
    }

    @Override
    public Either<Errors, UpdateCommentOutputBFF> process(UpdateCommentInputBFF input) {
        return validateInput(input).flatMap(validation -> updateContent(input));
    }

    private Either<Errors, UpdateCommentOutputBFF> updateContent(UpdateCommentInputBFF input) {
        return Try.of(() -> {
                    log.info("Start of getComments in bff with input {}", input);

                    UpdateCommentInput commentsInput = objectMapperConvertor.convert(input, UpdateCommentInput.class);
                    ResponseEntity<UpdateCommentOutput> response = commentsClient.updateComment(input.getId(), commentsInput);

                    UpdateCommentOutput responseResult = response.getBody();
                    UpdateCommentOutputBFF result = objectMapperConvertor.convert(responseResult, UpdateCommentOutputBFF.class);

                    log.info("End of getComments in bff with result {}", result);
                    return result;

                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
