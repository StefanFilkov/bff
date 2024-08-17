package com.tinqinacademy.bff.core.processors.hotel;


import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOutputBFF;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.exceptions.hotel.RoomNotFoundException;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
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
public class GetAvailableRoomsOperationProcessor extends BaseOperationProcessor implements GetFreeRoomsOperation {
    private final HotelClient hotelClient;
    protected GetAvailableRoomsOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient) {
        super(conversionService, validator, errorMapper);
        this.hotelClient = hotelClient;


    }

    @Override
    public Either<Errors, GetFreeRoomsOutputBFF> process(GetFreeRoomsInputBFF input) {
        return Try.of(() -> {

                    GetFreeRoomsOutputBFF result = GetFreeRoomsOutputBFF.builder().build();
                    return result;


        }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
