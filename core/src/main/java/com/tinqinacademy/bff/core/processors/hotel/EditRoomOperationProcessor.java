package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.editroom.EditRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.editroom.EditRoomOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.bff.api.operations.hotel.editroom.EditRoomOperation;

import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomInput;
import com.tinqinacademy.hotel.api.operations.editroom.EditRoomOutput;
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
public class EditRoomOperationProcessor extends BaseOperationProcessor implements EditRoomOperation {
    protected EditRoomOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, EditRoomOutputBFF> process(EditRoomInputBFF input) {
        return validateInput(input).flatMap(validation -> editRoom(input));
    }

    private Either<Errors, EditRoomOutputBFF> editRoom(EditRoomInputBFF input) {
        return Try.of(() -> {
                    log.info("start of editRoom in bff with input {}",input);

                    EditRoomInput hotelInput = objectMapperConvertor.convert(input, EditRoomInput.class);
                    ResponseEntity<EditRoomOutput> response = hotelClient.editRoom(hotelInput, input.getId());

                    EditRoomOutput responseResult = response.getBody();
                    EditRoomOutputBFF result = objectMapperConvertor.convert(responseResult, EditRoomOutputBFF.class);

                    log.info("end of editRoom in bff with result {}",result);
                    return result;



                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
