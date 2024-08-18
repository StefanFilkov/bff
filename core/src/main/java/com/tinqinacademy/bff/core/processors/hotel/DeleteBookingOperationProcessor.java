package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.deletebookingbyid.DeleteBookingByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.deletebookingbyid.DeleteBookingByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.deletebookingbyid.DeleteBookingByIdOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdInput;
import com.tinqinacademy.hotel.api.operations.deletebookingbyid.DeleteBookingByIdOutput;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;

@Service
@Slf4j
public class DeleteBookingOperationProcessor extends BaseOperationProcessor implements DeleteBookingByIdOperation {
    private final GenericResponseService responseBuilder;

    protected DeleteBookingOperationProcessor(ConversionService conversionService,
                                              Validator validator,
                                              ErrorMapper errorMapper,
                                              HotelClient hotelClient,
                                              ObjectMapperConvertor objectMapperConvertor, GenericResponseService responseBuilder) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
        this.responseBuilder = responseBuilder;
    }

    @Override
    public Either<Errors, DeleteBookingByIdOutputBFF> process(DeleteBookingByIdInputBFF input) {
        return validateInput(input).flatMap(validation -> deleteBooking(input));
    }

    private Either<Errors, DeleteBookingByIdOutputBFF> deleteBooking(DeleteBookingByIdInputBFF input) {
        return Try.of(() -> {
                    log.info("start of deleteBooking in bff with input {}",input);

                    DeleteBookingByIdInput hotelInput = objectMapperConvertor.convert(input, DeleteBookingByIdInput.class);
                    ResponseEntity<DeleteBookingByIdOutput> response = hotelClient.deleteBookingById(hotelInput.getId());


                    DeleteBookingByIdOutput responseResult = response.getBody();
                    DeleteBookingByIdOutputBFF result = objectMapperConvertor.convert(responseResult, DeleteBookingByIdOutputBFF.class);

                    log.info("end of deleteBooking in bff with result {}",result);
                    return result;



                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
