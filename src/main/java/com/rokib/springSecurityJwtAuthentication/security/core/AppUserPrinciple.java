package com.rokib.springSecurityJwtAuthentication.security.core;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rokib.springSecurityJwtAuthentication.persistence.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class AppUserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String userName;
    @JsonIgnore
    private String password;
    private boolean active;
    private Collection<? extends GrantedAuthority> authorities;

    public AppUserPrinciple(Long id, String name,
                            String username, String email, String password, boolean active,
                            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    public static AppUserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new AppUserPrinciple(
                user.getId(),
                user.getUserDisplayName(),
                user.getUserName(),
                user.getUserName(),
                user.getPassword(),
                user.isActive(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
