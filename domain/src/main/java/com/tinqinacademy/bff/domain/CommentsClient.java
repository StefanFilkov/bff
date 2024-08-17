package com.tinqinacademy.bff.domain;

import com.tinqinacademy.comments.restexport.CommentsExport;
import com.tinqinacademy.bff.domain.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "comments",
        url = "${comments.feign.client.url}",
        configuration = FeignConfiguration.class)

public interface CommentsClient extends CommentsExport {

}

