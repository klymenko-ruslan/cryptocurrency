package com.outsourcebooster.cryptocurrency.web.rest;

import com.outsourcebooster.cryptocurrency.web.dto.DTOTemplate;
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException;
import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> createUser(@RequestBody User user) {
        try {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User was successfully created.",userService.createUser(user)));
        } catch(NotUniqueEntityException e) {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User with such username already exists.", user));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/updateProfile", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> updateUserProfile(@RequestBody User user) {
        try {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User was successfully updated.",userService.updateUserProfile(user)));
        } catch(UnsupportedOperationException e) {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("There's no user with such username!", user));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/updateNotifications", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> updateUserNotifications(@RequestBody User user) {
        try {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User was successfully updated.",userService.updateUserNotificationRules(user)));
        } catch(UnsupportedOperationException e) {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("There's no user with such username!", user));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/updateNotificationRules", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> updateUserNotificationRules(@RequestBody User user) {
        try {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User was successfully updated.",userService.updateUserNotificationRules(user)));
        } catch(UnsupportedOperationException e) {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("There's no user with such username!", user));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public HttpEntity<User> getUser(HttpSession session) {
        SecurityContextImpl securityContext = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = ((UsernamePasswordAuthenticationToken)securityContext.getAuthentication()).getName();
        User user = userService.getUserByUsername(username);
        return new HttpEntity<User>(user);
    }
}
