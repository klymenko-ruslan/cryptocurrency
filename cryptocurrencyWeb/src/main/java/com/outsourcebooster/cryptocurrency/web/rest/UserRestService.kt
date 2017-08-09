package com.outsourcebooster.cryptocurrency.web.rest

import com.outsourcebooster.cryptocurrency.web.model.User
import com.outsourcebooster.cryptocurrency.web.dto.DTOTemplate
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException
import com.outsourcebooster.cryptocurrency.web.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * Created by rklimemnko on 29.05.2016.
 */
@RestController
open class UserRestService {
    //
    //    @Autowired
    //    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/create", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody user: User): HttpEntity<DTOTemplate<User>> {
        return HttpEntity(DTOTemplate("There's no user with such username!", user))

    }

    @ResponseBody
    @RequestMapping(value = "/user/updateProfile", method = arrayOf(RequestMethod.POST))
    fun updateUserProfile(@RequestBody user: User): HttpEntity<DTOTemplate<User>> {
        return HttpEntity(DTOTemplate("There's no user with such username!", user))

    }

    @ResponseBody
    @RequestMapping(value = "/user/updateNotificationRules", method = arrayOf(RequestMethod.POST))
    fun updateUserNotificationRules(@RequestBody user: User): HttpEntity<DTOTemplate<User>> {
        return HttpEntity(DTOTemplate("There's no user with such username!", user))

    }

    @ResponseBody
    @RequestMapping(value = "/user/get", method = arrayOf(RequestMethod.GET))
    fun getUser(session: HttpSession): HttpEntity<User> {
        val securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT") as SecurityContextImpl
        val username = (securityContext.authentication as UsernamePasswordAuthenticationToken).name
        val user: User? = null//userService.getUserByUsername(username);
        return HttpEntity<User>(user)
    }

    @ResponseBody
    @RequestMapping(value = "/user/notify", method = arrayOf(RequestMethod.POST))
    fun notify(session: HttpSession, request: HttpServletRequest): HttpEntity<User> {
        val securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT") as SecurityContextImpl
        val username = (securityContext.authentication as UsernamePasswordAuthenticationToken).name
        val user: User? = null//userService.getUserByUsername(username);
        return HttpEntity<User>(user)
    }
}
