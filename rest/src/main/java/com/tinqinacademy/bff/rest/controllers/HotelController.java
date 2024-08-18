package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.bookroombyid.ReserveRoomByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.bookroombyid.ReserveRoomByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.bookroombyid.ReserveRoomByIdOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.deletebookingbyid.DeleteBookingByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.deletebookingbyid.DeleteBookingByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.deletebookingbyid.DeleteBookingByIdOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getguestreport.GetGuestReportInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getguestreport.GetGuestReportOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestOperation;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.registerguests.AddGuestsOutputBFF;
import com.tinqinacademy.bff.api.urls.URLMappingsHotel;
import com.tinqinacademy.bff.core.processors.hotel.CreateRoomOperationProcessor;
import com.tinqinacademy.bff.core.processors.hotel.DeleteBookingOperationProcessor;
import com.tinqinacademy.bff.core.processors.hotel.GetGuestsReportOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;

import com.tinqinacademy.hotel.api.URLMappings;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdInput;
import com.tinqinacademy.hotel.api.operations.bookroombyid.ReserveRoomByIdOutput;
import com.tinqinacademy.hotel.api.operations.getguestreport.GetGuestReportInput;
import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
public class HotelController extends BaseController {


    private final CreateRoomOperation createRoomOperation;
    private final GetRoomByIdOperation getRoomByIdOperation;
    private final GetFreeRoomsOperation getFreeRoomsOperation;
    private final ReserveRoomByIdOperation reserveRoomByIdOperation;
    private final DeleteBookingByIdOperation deleteBookingOperation;
    private final AddGuestOperation addGuestOperation;
    private final GetGuestsReportOperationProcessor getGuestsReportOperationProcessor;

    public HotelController(CreateRoomOperation createRoomOperation, GetRoomByIdOperation getRoomByIdOperation, GetFreeRoomsOperation getFreeRoomsOperation, ReserveRoomByIdOperation reserveRoomByIdOperation, DeleteBookingByIdOperation deleteBookingOperation, AddGuestOperation addGuestOperation, GetGuestsReportOperationProcessor getGuestsReportOperationProcessor) {
        this.createRoomOperation = createRoomOperation;
        this.getRoomByIdOperation = getRoomByIdOperation;
        this.getFreeRoomsOperation = getFreeRoomsOperation;
        this.reserveRoomByIdOperation = reserveRoomByIdOperation;
        this.deleteBookingOperation = deleteBookingOperation;
        this.addGuestOperation = addGuestOperation;
        this.getGuestsReportOperationProcessor = getGuestsReportOperationProcessor;
    }


    @GetMapping(URLMappingsHotel.GET_ROOM)
    public ResponseEntity<?> getRoom(GetFreeRoomsInputBFF input) {
        Either<Errors, GetFreeRoomsOutputBFF> result = getFreeRoomsOperation.process(input);
        return handleResult(result);
    }
    @PostMapping(URLMappingsHotel.POST_BOOK_ROOM_BY_ID)
    ResponseEntity<?> bookRoom(@RequestBody ReserveRoomByIdInputBFF input, @RequestParam String roomId){
        input.setRoomId(roomId);
        Either<Errors, ReserveRoomByIdOutputBFF> result = reserveRoomByIdOperation.process(input);
        return handleResult(result);
    }

    @GetMapping(URLMappingsHotel.GET_ROOM_BY_ID)
    public ResponseEntity<?> getRoomById(@PathVariable String roomId) {
        GetRoomByIdInputBFF input = GetRoomByIdInputBFF.builder()
                .id(roomId).build();

        Either<Errors, GetRoomByIdOutputBFF> result = getRoomByIdOperation.process(input);
        return handleResult(result);
    }

    @DeleteMapping(URLMappingsHotel.DELETE_BOOKING_BY_ID)
    public ResponseEntity<?> deleteBooking(@PathVariable String bookingId) {
        DeleteBookingByIdInputBFF input =DeleteBookingByIdInputBFF.builder().id(bookingId).build();
        Either<Errors, DeleteBookingByIdOutputBFF> result = deleteBookingOperation.process(input);
        return handleResult(result);
    }

    @GetMapping(URLMappingsHotel.GET_REPORT)
    public ResponseEntity<?> getReport(@RequestParam LocalDate startDate,
                                       @RequestParam LocalDate endDate,
                                       @RequestParam(required = false) String roomNumber,
                                       @RequestParam(required = false) String cardIdN,
                                       @RequestParam(required = false) String cardIssueDate,
                                       @RequestParam(required = false) String cardIssueAuth,
                                       @RequestParam(required = false) String cardValidityDate,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String phoneN,
                                       @RequestParam(required = false) String birthdate
    ) {
        GetGuestReportInputBFF input = GetGuestReportInputBFF
                .builder()
                .roomN(roomNumber)
                .startDate(startDate)
                .cardIdN(cardIdN)
                .cardIssueDate(cardIssueDate)
                .cardIssueAuthority(cardIssueAuth)
                .cardValidityDate(cardValidityDate)
                .lastName(lastName)
                .firstName(firstName)
                .phoneN(phoneN)
                .endDate(endDate)
                .birthdate(birthdate)
                .build();
        Either<Errors, GetGuestReportOutputBFF> result = getGuestsReportOperationProcessor.process(input);
        return handleResult(result);
    }

    @PostMapping(URLMappingsHotel.POST_REGISTER_VISITOR)
    public ResponseEntity<?> addGuests(@RequestBody AddGuestsInputBFF inputBFF){
        Either<Errors, AddGuestsOutputBFF> result = addGuestOperation.process(inputBFF);
        return handleResult(result);
    }

    @PostMapping(URLMappingsHotel.POST_CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomInputBFF input) {

        Either<Errors, CreateRoomOutputBFF> result = createRoomOperation.process(input);
        return handleResult(result);
    }
}


