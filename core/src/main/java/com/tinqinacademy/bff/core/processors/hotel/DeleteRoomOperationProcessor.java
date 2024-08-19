package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.deleteroom.DeleteRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.deleteroom.DeleteRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.deleteroom.DeleteRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.updateroom.UpdateRoomOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.deleteroom.DeleteRoomOutput;
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
public class DeleteRoomOperationProcessor extends BaseOperationProcessor implements DeleteRoomOperation {
    protected DeleteRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, DeleteRoomOutputBFF> process(DeleteRoomInputBFF input) {
        return validateInput(input).flatMap(validation -> deleteRoom(input));
    }

    private Either<Errors, DeleteRoomOutputBFF> deleteRoom(DeleteRoomInputBFF input) {
        return Try.of(() -> {
                    log.info("start of editRoom in bff with input {}", input);

                    DeleteRoomInput hotelInput = objectMapperConvertor.convert(input, DeleteRoomInput.class);
                    ResponseEntity<DeleteRoomOutput> response = hotelClient.deleteRoom(hotelInput);

                    DeleteRoomOutput responseResult = response.getBody();
                    DeleteRoomOutputBFF result = objectMapperConvertor.convert(responseResult, DeleteRoomOutputBFF.class);

                    log.info("end of editRoom in bff with result {}", result);
                    return result;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}

