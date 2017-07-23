package com.outsourcebooster.cryptocurrency.web.rest;

import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.dto.DTOTemplate;
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException;
import com.outsourcebooster.cryptocurrency.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@RestController
public class UserRestService {
//
//    @Autowired
//    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> createUser(@RequestBody User user) {
        return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("There's no user with such username!", user));

    }

    @ResponseBody
    @RequestMapping(value = "/user/updateProfile", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> updateUserProfile(@RequestBody User user) {
        return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("There's no user with such username!", user));

    }

    @ResponseBody
    @RequestMapping(value = "/user/updateNotificationRules", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> updateUserNotificationRules(@RequestBody User user) {
        return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("There's no user with such username!", user));

    }

    @ResponseBody
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public HttpEntity<User> getUser(HttpSession session) {
        SecurityContextImpl securityContext = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = ((UsernamePasswordAuthenticationToken)securityContext.getAuthentication()).getName();
        User user = null;//userService.getUserByUsername(username);
        return new HttpEntity<User>(user);
    }

    @ResponseBody
    @RequestMapping(value = "/user/notify", method = RequestMethod.POST)
    public HttpEntity<User> notify(HttpSession session, HttpServletRequest request) {
        SecurityContextImpl securityContext = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = ((UsernamePasswordAuthenticationToken)securityContext.getAuthentication()).getName();
        User user = null;//userService.getUserByUsername(username);
        return new HttpEntity<User>(user);
    }
}
