package com.rokib.springSecurityJwtAuthentication.rest;

import com.rokib.springSecurityJwtAuthentication.dto.auth.request.SignInCommand;
import com.rokib.springSecurityJwtAuthentication.dto.auth.response.SignInResponse;
import com.rokib.springSecurityJwtAuthentication.service.contract.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInCommand signInCommand) {
        return ResponseEntity.ok(authService.authenticateUser(signInCommand));
    }
}
