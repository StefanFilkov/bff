package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.base.OperationProcessor;
import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.updateroom.UpdateRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.updateroom.UpdateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.updateroom.UpdateRoomOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;

import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.operations.updateroom.UpdateRoomOutput;
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
public class UpdateRoomOperationProcessor extends BaseOperationProcessor implements UpdateRoomOperation {

    protected UpdateRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, UpdateRoomOutputBFF> process(UpdateRoomInputBFF input) {
        return validateInput(input).flatMap(validation -> updateRoom(input));
    }

    private Either<Errors, UpdateRoomOutputBFF> updateRoom(UpdateRoomInputBFF input) {
        return Try.of(() -> {
                    log.info("start of editRoom in bff with input {}", input);

                    UpdateRoomInput hotelInput = objectMapperConvertor.convert(input, UpdateRoomInput.class);
                    ResponseEntity<UpdateRoomOutput> response = hotelClient.updateRoom(hotelInput, input.getId());

                    UpdateRoomOutput responseResult = response.getBody();
                    UpdateRoomOutputBFF result = objectMapperConvertor.convert(responseResult, UpdateRoomOutputBFF.class);

                    log.info("end of editRoom in bff with result {}", result);
                    return result;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
