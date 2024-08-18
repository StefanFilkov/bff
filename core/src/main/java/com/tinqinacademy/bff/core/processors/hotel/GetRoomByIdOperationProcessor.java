package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.exceptions.hotel.RoomNotFoundException;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdOutput;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;
@Service
@Slf4j
public class GetRoomByIdOperationProcessor extends BaseOperationProcessor implements GetRoomByIdOperation {


    protected GetRoomByIdOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, GetRoomByIdOutputBFF> process(GetRoomByIdInputBFF input) {
        return validateInput(input).flatMap(validation -> getRoomById(input));
    }

    private Either<Errors,GetRoomByIdOutputBFF> getRoomById(GetRoomByIdInputBFF input) {

        return Try.of(() -> {
                    log.info("start of getRoomById in bff with input {}",input);

                    GetRoomByIdOutput notFinalResult = hotelClient.getRoomById(input.getId()).getBody();

                    GetRoomByIdOutputBFF finalResult = objectMapperConvertor.convert(notFinalResult, GetRoomByIdOutputBFF.class);

                    log.info("end of getRoomById in bff with result {}",finalResult);
                    return finalResult;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }


}
