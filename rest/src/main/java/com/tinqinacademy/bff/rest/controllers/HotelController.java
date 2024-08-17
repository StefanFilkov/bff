package com.tinqinacademy.bff.rest.controllers;

import com.tinqinacademy.bff.domain.CommentsClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HotelController {
private final CommentsClient commentsClient;

    public HotelController(CommentsClient commentsClient) {
        this.commentsClient = commentsClient;
    }

    @GetMapping("/register")
    public ResponseEntity<?> registerUser( String input) {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}

