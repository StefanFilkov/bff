package com.tinqinacademy.domain;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "hotel", url = "http://localhost:8081")
public interface HotelClient {
    
}