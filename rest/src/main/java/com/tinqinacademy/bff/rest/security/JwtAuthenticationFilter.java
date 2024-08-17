package com.tinqinacademy.bff.rest.security;


import com.tinqinacademy.authentication.api.operations.validation.ValidationInput;
import com.tinqinacademy.bff.domain.AuthenticationClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Map;
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;

    private final JwtService jwtService;
    private final AuthenticationClient authenticationClient;

    public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver,
                                   JwtService jwtService,
                                   AuthenticationClient authenticationClient) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtService = jwtService;
        this.authenticationClient = authenticationClient;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {



        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (authentication == null) {
                ValidationInput isTokenValid = ValidationInput.builder()
                        .token(jwt)
                        .build();
                if (authenticationClient.validateToken(isTokenValid).getBody().getIsTokenValid()) {

                    Map<String, String> claims = jwtService.getPayloadMap(jwt);

                    log.debug("Token payload is {}", claims);

                    UserPrincipal userPrincipal = UserPrincipal.builder()
                            .role(claims.get("role"))
                            .userId(claims.get("userId"))
                            .username(claims.get("username"))
                            .build();


                    CustomToken userToken =new CustomToken(userPrincipal);

                    SecurityContextHolder.getContext().setAuthentication(userToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}