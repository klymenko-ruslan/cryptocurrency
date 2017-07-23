package com.outsourcebooster.cryptocurrency.web.repository;

import java.util.Collection;
import java.util.stream.Collectors;

import com.outsourcebooster.cryptocurrency.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByPassword(String password);
    User findByEmail(String email);
}
