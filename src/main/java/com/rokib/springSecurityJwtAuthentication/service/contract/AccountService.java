package com.rokib.springSecurityJwtAuthentication.service.contract;

public interface AccountService {

    void createUser(
            final String firstName,
            final String lastName,
            final String userName,
            final String password);
}
