package com.rokib.springSecurityJwtAuthentication.service.contract;

import com.rokib.springSecurityJwtAuthentication.dto.auth.request.SignInCommand;
import com.rokib.springSecurityJwtAuthentication.dto.auth.response.SignInResponse;

public interface AuthService {

    SignInResponse authenticateUser(SignInCommand signInCommand);

}
