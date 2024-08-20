package com.tinqinacademy.bff.core.processors.comments;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.comments.postcomment.PostCommentOutputBFF;
import com.tinqinacademy.bff.api.operations.comments.putcomment.PutCommentInputBFF;
import com.tinqinacademy.bff.api.operations.comments.putcomment.PutCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.putcomment.PutCommentOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentInput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentOutput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentInput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentOutput;
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
public class PutCommentOperationProcessor extends BaseOperationProcessor implements PutCommentOperation {
    private final CommentsClient commentsClient;

    protected PutCommentOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor, CommentsClient commentsClient) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
        this.commentsClient = commentsClient;
    }

    @Override
    public Either<Errors, PutCommentOutputBFF> process(PutCommentInputBFF input) {
        return validateInput(input).flatMap(validation -> putComment(input));
    }

    private Either<Errors, PutCommentOutputBFF> putComment(PutCommentInputBFF input) {
        return Try.of(() -> {
                    log.info("Start of putComment in bff with input {}", input);

                    PutCommentInput commentsInput = objectMapperConvertor.convert(input, PutCommentInput.class);
                    ResponseEntity<PutCommentOutput> response = commentsClient.putComment(commentsInput,input.getId());

                    PutCommentOutput responseResult = response.getBody();
                    PutCommentOutputBFF result = objectMapperConvertor.convert(responseResult, PutCommentOutputBFF.class);

                    log.info("End of putComment in bff with result {}", result);
                    return result;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
