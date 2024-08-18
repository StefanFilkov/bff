package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.api.errors.Errors;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOperation;
import com.tinqinacademy.bff.api.operations.hotel.createroom.CreateRoomOutputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsInputBFF;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOperation;
import com.tinqinacademy.bff.api.operations.hotel.getfreerooms.GetFreeRoomsOutputBFF;
import com.tinqinacademy.bff.api.urls.URLMappingsHotel;
import com.tinqinacademy.bff.core.processors.hotel.CreateRoomOperationProcessor;
import com.tinqinacademy.bff.domain.CommentsClient;

import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HotelController extends BaseController {

    private final CommentsClient commentsClient;
    private final CreateRoomOperation createRoomOperation;

    private final GetFreeRoomsOperation getFreeRoomsOperation;


    public HotelController(CommentsClient commentsClient, CreateRoomOperation createRoomOperation, GetFreeRoomsOperation getFreeRoomsOperation) {
        this.commentsClient = commentsClient;

        this.createRoomOperation = createRoomOperation;
        this.getFreeRoomsOperation = getFreeRoomsOperation;
    }

    @GetMapping(URLMappingsHotel.GET_ROOM)
    public ResponseEntity<?> test(GetFreeRoomsInputBFF input) {
        Either<Errors, GetFreeRoomsOutputBFF> result = getFreeRoomsOperation.process(input);
        return handleResult(result);
    }

    @GetMapping("/register")
    public ResponseEntity<?> registerUser(String input) {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    @PostMapping(URLMappingsHotel.POST_CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomInputBFF input) {

        Either<Errors, CreateRoomOutputBFF> result = createRoomOperation.process(input);
        return handleResult(result);
    }
}


