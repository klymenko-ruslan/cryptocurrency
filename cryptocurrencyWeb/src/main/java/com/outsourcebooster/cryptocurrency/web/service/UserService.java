package com.outsourcebooster.cryptocurrency.web.service;

import com.outsourcebooster.cryptocurrency.web.config.security.constant.SecurityRole;
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException;
import com.outsourcebooster.cryptocurrency.web.repository.UserRepository;
import com.outsourcebooster.cryptocurrency.web.model.*;
import com.outsourcebooster.cryptocurrency.web.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

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
        if(userRepository.findByEmail(user.getEmail()) != null)
            throw new NotUniqueEntityException("This email is already in use.");
        user.setCreatedDate(new Date());
        user.setRoles(Arrays.asList(new String[]{SecurityRole.USER}));
        user.setWallet(new Wallet(new ArrayList<Account>()));
        user.setUserCurrency(UserCurrency.usd);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        new Thread(() -> ApplicationUtils.sendEmail(ACTIVATION_SUBJECT,
                ACTIVATION_MESSAGE.replace("${link}",generateActivationLink(createdUser.getPassword())),
                createdUser.getEmail())).start();
        return createdUser;
    }

    public void activateUser(String activationProperty) {
        User activatedUser = userRepository.findByPassword(activationProperty);
        if(activatedUser != null) {
            activatedUser.setIsActive(true);
            userRepository.save(activatedUser);
        }
    }

    private String generateActivationLink(String decodedPassword) {
        StringBuilder url = new StringBuilder();
        url.append(ApplicationUtils.getCommonProperty("cryptocurrency.web.url"))
            .append(":")
            .append(ApplicationUtils.getCommonProperty("cryptocurrency.web.port"))
            .append("/activate?activationProperty=")
            .append(decodedPassword);
        return url.toString();
    }

    private static final String ACTIVATION_SUBJECT = "Activation";
    private static final String ACTIVATION_MESSAGE = "Please follow this link to finish your registration: <a href='${link}'>Activate</a>";


    public User updateUserProfile(User updatedUser) {
        User savedUser = userRepository.findByEmail(updatedUser.getEmail());
        if(savedUser == null)
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
        User savedUser = userRepository.findByEmail(updatedUser.getEmail());
        if(savedUser == null)
            throw new UnsupportedOperationException("There's no updatedUser with such username!");
        savedUser.setNotificationRules(updatedUser.getNotificationRules());
        userRepository.save(savedUser);
        return savedUser;
    }

    public User updateUserImageFile(String email, MultipartFile file) throws IOException {
        User savedUser = userRepository.findByEmail(email);
        if(savedUser == null)
            throw new UnsupportedOperationException("There's no updatedUser with such username!");

        String pathToUserImages = ApplicationUtils.getCommonProperty("cryptocurrency.web.images.path");
        if(savedUser.getImageFileName() != null)
            Paths.get(pathToUserImages, savedUser.getImageFileName()).toFile().delete();
        String newImageFileName = email + System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Files.copy(file.getInputStream(), Paths.get(pathToUserImages, newImageFileName));

        savedUser.setImageFileName(newImageFileName);
        userRepository.save(savedUser);

        return savedUser;
    }

    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public Collection<GrantedAuthority> getUserAuthorities(String username, String password) {
        User user = getUserByUsername(username);
        return user == null ?  Collections.emptySet() :
                user.getRoles()
                    .stream().map(role -> new GrantedAuthority(){
                    @Override
                    public String getAuthority() {
                        return role;
                    }
                }).collect(Collectors.toSet());
    }
}