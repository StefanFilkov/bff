package com.tinqinacademy.domain;


import com.tinqinacademy.comments.restexport.Comments;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "comments",
//        name = "comments",
        url = "${comments.feign.client.url}")
public interface CommentsClient extends Comments {

}

