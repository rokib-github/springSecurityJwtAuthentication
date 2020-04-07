package com.rokib.springSecurityJwtAuthentication.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInResponse {
    private String access_token;
    private String token_type;
}
