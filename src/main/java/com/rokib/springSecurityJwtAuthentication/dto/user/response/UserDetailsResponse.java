package com.rokib.springSecurityJwtAuthentication.dto.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsResponse {
    private String userName;
    private String displayName;
    private boolean active;
}
