package com.tinqinacademy.bff.domain;

import com.tinqinacademy.authentication.restexport.AuthenticationRestExport;
import com.tinqinacademy.bff.domain.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "authentication",
        url = "${auth.feign.client.url}",
        configuration = FeignConfiguration.class)
public interface AuthenticationClient extends AuthenticationRestExport {
}
