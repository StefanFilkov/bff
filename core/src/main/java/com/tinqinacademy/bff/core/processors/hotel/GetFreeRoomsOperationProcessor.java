package com.tinqinacademy.bff.core.processors.hotel;


import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.exceptions.hotel.RoomNotFoundException;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.getfreerooms.GetFreeRoomsOutput;
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
public class GetFreeRoomsOperationProcessor extends BaseOperationProcessor implements GetFreeRoomsOperation {

    private final HotelClient hotelClient;
    private final ObjectMapperConvertor objectMapperConvertor;

    protected GetFreeRoomsOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper);
        this.hotelClient = hotelClient;


        this.objectMapperConvertor = objectMapperConvertor;
    }

    @Override
    public Either<Errors, GetFreeRoomsOutputBFF> process(GetFreeRoomsInputBFF input) {
        return Try.of(() -> {
                    log.info("start of getfreeRooms in bff with input {}",input);

                    GetFreeRoomsOutput notFinalResult = hotelClient.getFreeRooms(input.getStartDate(), input.getEndDate(), input.getBedSizes(), input.getBathroomTypes()).getBody();

                    GetFreeRoomsOutputBFF finalResult = objectMapperConvertor.convert(notFinalResult, GetFreeRoomsOutputBFF.class);

                    log.info("end of getfreeRooms in bff with result {}",finalResult);
                    return finalResult;


        }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(RoomNotFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
