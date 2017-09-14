package com.outsourcebooster.cryptocurrency.web.config.security.controller;


import com.outsourcebooster.cryptocurrency.web.config.security.model.JwtAuthenticationRequest;
import com.outsourcebooster.cryptocurrency.web.config.security.util.JwtTokenUtil;
import com.outsourcebooster.cryptocurrency.web.config.security.model.JwtAuthenticationResponse;
import com.outsourcebooster.cryptocurrency.web.service.user.UserService
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
open class AuthenticationRestController(private val authenticationManager: AuthenticationManager,
                                        private val jwtTokenUtil: JwtTokenUtil,
                                        private val userDetailsService: UserDetailsService,
                                        private val userService: UserService) {


    @RequestMapping(value = "auth", method = arrayOf(RequestMethod.POST))
    fun  createAuthenticationToken(@RequestBody  authenticationRequest: JwtAuthenticationRequest): ResponseEntity<JwtAuthenticationResponse> {
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
        )
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        SecurityContextHolder.getContext().setAuthentication(authentication)
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val token = jwtTokenUtil.generateToken(userDetails)
        val user = userService.getUserByEmail(authenticationRequest.email)
        user!!.password = ""
        return ResponseEntity.ok(JwtAuthenticationResponse(token, user))
    }
}
