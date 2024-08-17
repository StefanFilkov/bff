package com.tinqinacademy.bff.domain;

import com.tinqinacademy.bff.domain.configuration.FeignConfiguration;
import com.tinqinacademy.hotel.restexport.HotelExport;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "comments",
        url = "${hotel.feign.client.url}",
        configuration = FeignConfiguration.class)

public interface HotelClient extends HotelExport {
}
