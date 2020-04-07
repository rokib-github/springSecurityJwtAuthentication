package com.rokib.springSecurityJwtAuthentication.dto.auth.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignInCommand {
    @NotEmpty
    @Email
    private String userName;
    @NotEmpty
    @Size(min=4)
    private String password;
}
