package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.bookroombyid.ReserveRoomByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.bookroombyid.ReserveRoomByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.bookroombyid.ReserveRoomByIdOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.exceptions.hotel.RoomNotFoundException;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsOutput;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Slf4j
@Service
public class BookRoomOperationProcessor extends BaseOperationProcessor implements ReserveRoomByIdOperation {


    protected BookRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, ReserveRoomByIdOutputBFF> process(ReserveRoomByIdInputBFF input) {
        return validateInput(input).flatMap(validation -> bookById(input));
    }

    private Either<Errors,ReserveRoomByIdOutputBFF> bookById(ReserveRoomByIdInputBFF input) {
        return Try.of(() -> {
                    log.info("start of bookById in bff with input {}", input);

                    ReserveRoomByIdOutput notFinalResult = hotelClient.bookRoom(objectMapperConvertor.convert(input, ReserveRoomByIdInput.class),input.getRoomId()).getBody();

                    ReserveRoomByIdOutputBFF finalResult = objectMapperConvertor.convert(notFinalResult, ReserveRoomByIdOutputBFF.class);

                    log.info("end of bookById in bff with result {}", finalResult);
                        return finalResult;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
