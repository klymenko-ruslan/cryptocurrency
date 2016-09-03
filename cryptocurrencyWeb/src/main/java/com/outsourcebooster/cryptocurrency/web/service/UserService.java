package com.outsourcebooster.cryptocurrency.web.service;

import com.outsourcebooster.cryptocurrency.web.config.MvcConfig;
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException;
import com.outsourcebooster.cryptocurrency.web.repository.UserRepository;
import com.outsourcebooster.cryptocurrency.web.model.*;
import com.outsourcebooster.cryptocurrency.web.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        user.setCreatedDate(new Date());
        user.setRoles(Arrays.asList(new Role[]{Role.user}));
        user.setWallet(new Wallet(new ArrayList<Account>()));
        user.setUserCurrency(UserCurrency.usd);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUserProfile(User updatedUser) {
        User savedUser = userRepository.findByUsername(updatedUser.getUsername());
        if(userRepository.findByUsername(updatedUser.getUsername()) == null)
            throw new UnsupportedOperationException("There's no updatedUser with such username!");
        savedUser.setFirstName(updatedUser.getFirstName());
        savedUser.setLastName(updatedUser.getLastName());
        savedUser.setProfession(updatedUser.getProfession());
        savedUser.setEmail(updatedUser.getEmail());
        savedUser.setUserCurrency(updatedUser.getUserCurrency());
        savedUser.setEnableNotification(updatedUser.isEnableNotification());
        if(updatedUser.isEnableNotification()) {
            savedUser.setNotificationRules(null);
        }
        userRepository.save(savedUser);
        return savedUser;
    }

    public User updateUserNotificationRules(User updatedUser) {
        User savedUser = userRepository.findByUsername(updatedUser.getUsername());
        if(userRepository.findByUsername(updatedUser.getUsername()) == null)
            throw new UnsupportedOperationException("There's no updatedUser with such username!");
        savedUser.setNotificationRules(updatedUser.getNotificationRules());
        userRepository.save(savedUser);
        return savedUser;
    }

    public User updateUserImageFile(String username, MultipartFile file) throws IOException {
        User savedUser = userRepository.findByUsername(username);
        if(savedUser == null)
            throw new UnsupportedOperationException("There's no updatedUser with such username!");

        String pathToUserImages = ApplicationUtils.getCommonProperty("cryptocurrency.web.images.path");
        if(savedUser.getImageFileName() != null)
            Paths.get(pathToUserImages, savedUser.getImageFileName()).toFile().delete();
        String newImageFileName = username + System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Files.copy(file.getInputStream(), Paths.get(pathToUserImages, newImageFileName));

        savedUser.setImageFileName(newImageFileName);
        userRepository.save(savedUser);

        return savedUser;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
