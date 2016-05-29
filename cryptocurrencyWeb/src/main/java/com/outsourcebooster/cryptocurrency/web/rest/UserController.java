package com.outsourcebooster.cryptocurrency.web.rest;

import com.outsourcebooster.cryptocurrency.web.dto.DTOTemplate;
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException;
import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rklimemnko on 29.05.2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public HttpEntity<DTOTemplate<User>> createUser(@RequestBody User user) {
        try {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User was successfully created.",userService.createUser(user)));
        } catch(NotUniqueEntityException e) {
            return new HttpEntity<DTOTemplate<User>>(new DTOTemplate<User>("User with such username already exists.", user));
        }
    }
}
