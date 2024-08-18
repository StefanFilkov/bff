package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdOperation;
import com.tinqinacademy.bff.api.operations.hotel.getroombyid.GetRoomByIdOutputBFF;
import com.tinqinacademy.bff.api.urls.URLMappingsHotel;
import com.tinqinacademy.bff.core.processors.hotel.CreateRoomOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;

import com.tinqinacademy.hotel.api.operations.getroombyid.GetRoomByIdInput;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HotelController extends BaseController {


    private final CreateRoomOperation createRoomOperation;
    private final GetRoomByIdOperation getRoomByIdOperation;
    private final GetFreeRoomsOperation getFreeRoomsOperation;


    public HotelController(CreateRoomOperation createRoomOperation, GetRoomByIdOperation getRoomByIdOperation, GetFreeRoomsOperation getFreeRoomsOperation) {
        this.getRoomByIdOperation = getRoomByIdOperation;


        this.createRoomOperation = createRoomOperation;
        this.getFreeRoomsOperation = getFreeRoomsOperation;
    }

    @GetMapping(URLMappingsHotel.GET_ROOM)
    public ResponseEntity<?> getRoom(GetFreeRoomsInputBFF input) {
        Either<Errors, GetFreeRoomsOutputBFF> result = getFreeRoomsOperation.process(input);
        return handleResult(result);
    }

    @GetMapping(URLMappingsHotel.GET_ROOM_BY_ID)
    public ResponseEntity<?> getRoomById(@PathVariable String roomId) {
        GetRoomByIdInputBFF input = GetRoomByIdInputBFF.builder()
                .id(roomId).build();

        Either<Errors, GetRoomByIdOutputBFF> result = getRoomByIdOperation.process(input);
        return handleResult(result);
    }

    @PostMapping(URLMappingsHotel.POST_CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomInputBFF input) {

        Either<Errors, CreateRoomOutputBFF> result = createRoomOperation.process(input);
        return handleResult(result);
    }
}


