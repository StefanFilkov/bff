package com.tinqinacademy.bff.rest.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class CustomToken extends AbstractAuthenticationToken {
    private UserPrincipal userPrincipal;


    public CustomToken(UserPrincipal userPrincipal) {
        super(Collections.singletonList(new SimpleGrantedAuthority(String.format("ROLE_%s", userPrincipal.getRole()))));
        super.setAuthenticated(true);

        this.userPrincipal = userPrincipal;


    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }
}
