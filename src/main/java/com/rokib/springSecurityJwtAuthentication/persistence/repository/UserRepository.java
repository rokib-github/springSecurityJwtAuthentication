package com.rokib.springSecurityJwtAuthentication.persistence.repository;

import com.rokib.springSecurityJwtAuthentication.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
