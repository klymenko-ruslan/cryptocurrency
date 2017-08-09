package com.outsourcebooster.cryptocurrency.web.config.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
open class JwtAuthenticationEntryPoint: AuthenticationEntryPoint {

    @Override
    override fun commence(request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException) = response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
}