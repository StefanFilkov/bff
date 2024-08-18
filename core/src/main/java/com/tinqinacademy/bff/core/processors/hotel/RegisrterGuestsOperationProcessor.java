package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.models.inputs.GuestInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestOperation;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestsOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.registerguests.AddGuestInput;
import com.tinqinacademy.hotel.api.operations.registerguests.AddGuestsOutput;
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
public class RegisrterGuestsOperationProcessor extends BaseOperationProcessor implements AddGuestOperation {
    protected RegisrterGuestsOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, AddGuestsOutputBFF> process(AddGuestsInputBFF input) {
        return validateInput(input).flatMap(validation -> addGuests(input));
    }

    private Either<Errors, AddGuestsOutputBFF> addGuests(AddGuestsInputBFF input) {
        return Try.of(() -> {
                    log.info("start of addGuests in bff with input {}",input);

                    AddGuestInput hotelInput = conversionService.convert(input, AddGuestInput.class);
                    ResponseEntity<AddGuestsOutput> response = hotelClient.registerVisitors(hotelInput);

                    AddGuestsOutput responseBody = response.getBody();
                    AddGuestsOutputBFF result = conversionService.convert(responseBody, AddGuestsOutputBFF.class);

                    log.info("end of addGuests in bff with result {}",result);
                    return result;



                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));


    }
}
