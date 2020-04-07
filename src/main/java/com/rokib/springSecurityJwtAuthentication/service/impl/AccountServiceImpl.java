package com.rokib.springSecurityJwtAuthentication.service.impl;

import com.rokib.springSecurityJwtAuthentication.persistence.entity.User;
import com.rokib.springSecurityJwtAuthentication.persistence.repository.UserRepository;
import com.rokib.springSecurityJwtAuthentication.service.contract.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public AccountServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void createUser(String firstName, String lastName, String userName, String password) {

        User user = new User();
        user.setUserDisplayName(firstName + " " + lastName);
        user.setUserName(userName);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setActive(true);

        userRepository.save(user);
    }
}
