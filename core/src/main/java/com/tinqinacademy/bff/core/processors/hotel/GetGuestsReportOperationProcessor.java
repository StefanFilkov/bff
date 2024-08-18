package com.tinqinacademy.bff.core.processors.hotel;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getguestreport.GetGuestReportInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getguestreport.GetGuestReportOperation;
import com.tinqinacademy.bff.api.operations.hotel.getguestreport.GetGuestReportOutputBFF;
import com.tinqinacademy.bff.core.converters.ObjectMapperConvertor;
import com.tinqinacademy.bff.core.errormapper.ErrorMapper;
import com.tinqinacademy.bff.core.processors.BaseOperationProcessor;
import com.tinqinacademy.bff.domain.HotelClient;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportInput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportOutput;
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
public class GetGuestsReportOperationProcessor extends BaseOperationProcessor implements GetGuestReportOperation {
    protected GetGuestsReportOperationProcessor(ConversionService conversionService, Validator validator, ErrorMapper errorMapper, HotelClient hotelClient, ObjectMapperConvertor objectMapperConvertor) {
        super(conversionService, validator, errorMapper, hotelClient, objectMapperConvertor);
    }

    @Override
    public Either<Errors, GetGuestReportOutputBFF> process(GetGuestReportInputBFF input) {
        return validateInput(input).flatMap(validation -> getReport(input));
    }

    private Either<Errors, GetGuestReportOutputBFF> getReport(GetGuestReportInputBFF input) {
        return Try.of(() -> {
                    log.info("start of getReport in bff with input {}", input);

                    GetGuestReportInput hotelInput = objectMapperConvertor.convert(input, GetGuestReportInput.class);
                    ResponseEntity<GetGuestReportOutput> response = hotelClient.getRegisteredUser(
                            hotelInput.getStartDate(),
                            hotelInput.getEndDate(),
                            hotelInput.getRoomN(),
                            hotelInput.getCardIdN(),
                            hotelInput.getCardIssueDate(),
                            hotelInput.getCardIssueAuthority(),
                            hotelInput.getCardValidityDate(),
                            hotelInput.getLastName(),
                            hotelInput.getFirstName(),
                            hotelInput.getPhoneN(),
                            hotelInput.getBirthdate());


                    GetGuestReportOutput responseResult = response.getBody();
                    GetGuestReportOutputBFF result = objectMapperConvertor.convert(responseResult, GetGuestReportOutputBFF.class);


                    log.info("end of getReport in bff with result {}", result);
                    return result;


                }).toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), errorMapper::mapErrors)
                ));
    }
}
