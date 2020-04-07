package com.rokib.springSecurityJwtAuthentication.rest;

import com.rokib.springSecurityJwtAuthentication.dto.user.response.UserDetailsResponse;
import com.rokib.springSecurityJwtAuthentication.security.core.AppUserPrinciple;
import com.rokib.springSecurityJwtAuthentication.service.contract.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/who-am-i")
    public ResponseEntity<UserDetailsResponse> whoAmI(
            @NotNull
            @AuthenticationPrincipal
            UsernamePasswordAuthenticationToken authentication){
        AppUserPrinciple user = (AppUserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok().body(userService.whoAmI(user.getId()));
    }

}
