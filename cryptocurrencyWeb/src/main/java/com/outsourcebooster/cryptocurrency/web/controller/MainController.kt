package com.outsourcebooster.cryptocurrency.web.controller

import com.outsourcebooster.cryptocurrency.web.service.user.UserService
import org.springframework.web.bind.annotation.RestController

/**
 * Created by rklimemnko on 24.05.2016.
 */
@RestController
class MainController(private val userService: UserService) {

}
