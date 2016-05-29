package com.outsourcebooster.cryptocurrency.web.service;

import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException;
import com.outsourcebooster.cryptocurrency.web.model.Role;
import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null)
            throw new NotUniqueEntityException("This username is already in use.");
        user.setRoles(Arrays.asList(new Role[]{Role.user}));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
