package com.rokib.springSecurityJwtAuthentication.security.core;

import com.rokib.springSecurityJwtAuthentication.persistence.entity.User;
import com.rokib.springSecurityJwtAuthentication.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + userName)
                );
        return AppUserPrinciple.build(user);
    }
}
