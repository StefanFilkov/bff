package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
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
public class CreateRoomOperationProcessor extends BaseOperationProcessor implements CreateRoomOperation {


    protected CreateRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, CreateRoomOutputBFF> process(CreateRoomInputBFF input) {
        return validateInput(input).flatMap(validation -> createRoom(input));
    }

    private Either<Errors, CreateRoomOutputBFF> createRoom(CreateRoomInputBFF input){
        return Try.of(() -> {
                    log.info("start of createRoom in bff with input {}",input);

                    CreateRoomInput hotelInput = objectMapperConvertor.convert(input, CreateRoomInput.class);
                    ResponseEntity<CreateRoomOutput> response = hotelClient.createRoom(hotelInput);

                    //TODO throws an exception
                    CreateRoomOutput responseResult = response.getBody();
                    CreateRoomOutputBFF result = objectMapperConvertor.convert(responseResult, CreateRoomOutputBFF.class);

                    log.info("end of createRoom in bff with result {}",result);
                    return result;



                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
