package com.tinqinacademy.bff.rest.security;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipal {
    private String userId;
    private String role;
    private String username;
}
