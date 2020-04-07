package com.rokib.springSecurityJwtAuthentication.service.contract;

import com.rokib.springSecurityJwtAuthentication.dto.user.response.UserDetailsResponse;

public interface UserService {
    UserDetailsResponse whoAmI(final long userId);
}
