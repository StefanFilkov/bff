package com.tinqinacademy.domain;

import org.springframework.stereotype.Service;

@Service
public class FeignService {
    private final CommentsClient commentsClient;
    private final HotelClient hotelClient;

    public FeignService(CommentsClient commentsClient, HotelClient hotelClient) {
        this.commentsClient = commentsClient;
        this.hotelClient = hotelClient;
    }

    public CommentsClient getCommentsClient() {
        return commentsClient;
    }
}

