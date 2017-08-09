package com.outsourcebooster.cryptocurrency.web.config.security

import com.outsourcebooster.cryptocurrency.web.config.security.filter.JwtAuthenticationEntryPoint
import com.outsourcebooster.cryptocurrency.web.config.security.filter.JwtAuthenticationTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurityConfig(private val unauthorizedHandler: JwtAuthenticationEntryPoint,
                             private val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {

    @Autowired
    @Throws(Exception::class)
    fun configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService<UserDetailsService>(this.userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    open fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    open fun authenticationTokenFilter() = JwtAuthenticationTokenFilter()

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .headers().cacheControl()
    }
}