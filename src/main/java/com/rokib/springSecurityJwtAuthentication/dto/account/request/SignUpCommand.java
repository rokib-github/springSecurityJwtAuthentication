package com.rokib.springSecurityJwtAuthentication.dto.account.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignUpCommand {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String userName;
    @NotEmpty
    @Size(min = 4)
    private String password;

}
