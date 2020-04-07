package com.rokib.springSecurityJwtAuthentication.service.impl;

import com.rokib.springSecurityJwtAuthentication.dto.auth.request.SignInCommand;
import com.rokib.springSecurityJwtAuthentication.dto.auth.response.SignInResponse;
import com.rokib.springSecurityJwtAuthentication.security.jwt.JwtUtils;
import com.rokib.springSecurityJwtAuthentication.service.contract.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public SignInResponse authenticateUser(SignInCommand signInCommand) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInCommand.getUserName(),
                        signInCommand.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new SignInResponse(jwt, "Bearer ");
    }
}
