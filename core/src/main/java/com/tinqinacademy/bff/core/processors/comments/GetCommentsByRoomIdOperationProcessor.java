package com.tinqinacademy.bff.core.processors.comments;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomInputBFF;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOperation;
import com.tinqinacademy.bff.api.operations.comments.getcommentbyroom.GetCommentsByRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.deleteroom.DeleteRoomOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomInput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOutput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
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
public class GetCommentsByRoomIdOperationProcessor extends BaseOperationProcessor implements GetCommentsByRoomOperation {

    private final CommentsClient commentsClient;

    protected GetCommentsByRoomIdOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor, CommentsClient commentsClient) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
        this.commentsClient = commentsClient;
    }

    @Override
    public Either<Errors, GetCommentsByRoomOutputBFF> process(GetCommentsByRoomInputBFF input) {
        return validateInput(input).flatMap(validation -> getComments(input));
    }

    private Either<Errors, GetCommentsByRoomOutputBFF> getComments(GetCommentsByRoomInputBFF input) {
        return Try.of(() -> {
                    log.info("start of getComments in bff with input {}", input);

                    GetCommentsByRoomInput commentsInput = objectMapperConvertor.convert(input, GetCommentsByRoomInput.class);
                    ResponseEntity<GetCommentsByRoomOutput> response = commentsClient.getCommentsByRoom(commentsInput.getId());

                    GetCommentsByRoomOutput responseResult = response.getBody();
                    GetCommentsByRoomOutputBFF result = objectMapperConvertor.convert(responseResult, GetCommentsByRoomOutputBFF.class);

                    log.info("end of getComments in bff with result {}", result);
                    return result;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
