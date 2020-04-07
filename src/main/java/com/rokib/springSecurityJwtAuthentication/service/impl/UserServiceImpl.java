package com.rokib.springSecurityJwtAuthentication.service.impl;

import com.rokib.springSecurityJwtAuthentication.dto.user.response.UserDetailsResponse;
import com.rokib.springSecurityJwtAuthentication.persistence.entity.User;
import com.rokib.springSecurityJwtAuthentication.persistence.repository.UserRepository;
import com.rokib.springSecurityJwtAuthentication.service.contract.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsResponse whoAmI(final long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No User Found With Id: " + userId));

        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse.setDisplayName(user.getUserDisplayName());
        userDetailsResponse.setUserName(user.getUserName());
        userDetailsResponse.setActive(user.isActive());
        return userDetailsResponse;
    }
}
