package com.outsourcebooster.cryptocurrency.web.config.security.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.outsourcebooster.cryptocurrency.web.config.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

open class JwtAuthenticationTokenFilter: OncePerRequestFilter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService
    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil
    @Value("\${jwt.header}")
    private lateinit var tokenHeader: String

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authToken = request.getHeader(this.tokenHeader)
        if(authToken != null) {
            val username = jwtTokenUtil.getUsernameFromToken(authToken)
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                val userDetails = this.userDetailsService.loadUserByUsername(username)
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                    SecurityContextHolder.getContext().setAuthentication(authentication)
                }
            }
        }
        chain.doFilter(request, response)
    }
}