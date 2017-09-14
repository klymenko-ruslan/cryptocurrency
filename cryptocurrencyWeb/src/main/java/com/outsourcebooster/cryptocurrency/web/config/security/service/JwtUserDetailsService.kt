package com.outsourcebooster.cryptocurrency.web.config.security.service;

import com.outsourcebooster.cryptocurrency.web.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
open class JwtUserDetailsService(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email);
        if(user == null)  throw UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        return object: UserDetails {
            override fun getAuthorities() = toGrantedAuthorities(user.roles)
            override fun getPassword() = user.password
            override fun getUsername() = user.email
            override fun isAccountNonExpired() = user.isActive
            override fun isAccountNonLocked() = user.isActive
            override fun isCredentialsNonExpired() = user.isActive
            override fun isEnabled() = user.isActive
        }
    }

    fun toGrantedAuthorities(roles: MutableCollection<String>): List<GrantedAuthority> {
        return roles.map{role ->
                            GrantedAuthority { role }
                        }
    }
}
