package com.outsourcebooster.cryptocurrency.web.rest.user

import com.outsourcebooster.cryptocurrency.web.model.user.User
import com.outsourcebooster.cryptocurrency.web.dto.DTOTemplate
import com.outsourcebooster.cryptocurrency.web.exception.NotUniqueEntityException
import com.outsourcebooster.cryptocurrency.web.exception.WrongPasswordException
import com.outsourcebooster.cryptocurrency.web.exception.WrongUsernameException
import com.outsourcebooster.cryptocurrency.web.service.user.UserService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * Created by rklimemnko on 29.05.2016.
 */
@RestController
open class UserRestController(private val userService: UserService) {

    @RequestMapping(value = "/user/create", method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody user: User): ResponseEntity<User> {
        try {
            return ResponseEntity(userService.create(user), HttpStatus.OK)
        } catch(e: NotUniqueEntityException) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @RequestMapping(value = "/user/update", method = arrayOf(RequestMethod.POST))
    fun update(@RequestBody user: User): ResponseEntity<User> {
        try {
            return ResponseEntity(userService.update(user), HttpStatus.OK)
        } catch(e: WrongUsernameException) {
            return ResponseEntity(HttpStatus.CONFLICT)
        } catch(e: WrongPasswordException) {
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
    }

    @RequestMapping(value = "/user/updateProfile", method = arrayOf(RequestMethod.POST))
    fun updateUserProfile(@RequestBody user: User): HttpEntity<DTOTemplate<User>> {
        return HttpEntity(DTOTemplate("There's no user with such username!", user))

    }

    @RequestMapping(value = "/user/updateNotificationRules", method = arrayOf(RequestMethod.POST))
    fun updateUserNotificationRules(@RequestBody user: User): HttpEntity<DTOTemplate<User>> {
        return HttpEntity(DTOTemplate("There's no user with such username!", user))

    }

    @RequestMapping(value = "/user/get", method = arrayOf(RequestMethod.GET))
    fun getUser(session: HttpSession): HttpEntity<User> {
        val securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT") as SecurityContextImpl
        val username = (securityContext.authentication as UsernamePasswordAuthenticationToken).name
        val user: User? = null//userService.getUserByEmail(username);
        return HttpEntity<User>(user)
    }

    @RequestMapping(value = "/user/notify", method = arrayOf(RequestMethod.POST))
    fun notify(session: HttpSession, request: HttpServletRequest): HttpEntity<User> {
        val securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT") as SecurityContextImpl
        val username = (securityContext.authentication as UsernamePasswordAuthenticationToken).name
        val user: User? = null//userService.getUserByEmail(username);
        return HttpEntity<User>(user)
    }
}
