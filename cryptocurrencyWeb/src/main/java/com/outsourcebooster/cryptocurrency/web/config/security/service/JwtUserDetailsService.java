package com.outsourcebooster.cryptocurrency.web.config.security.service;

import java.util.Collection;
import java.util.stream.Collectors;

import com.outsourcebooster.cryptocurrency.web.model.User;
import com.outsourcebooster.cryptocurrency.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)  throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return toGrantedAuthorities(user.getRoles());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.isActive();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isActive();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.isActive();
            }

            @Override
            public boolean isEnabled() {
                return user.isActive();
            }
        };
        return userDetails;
    }

    public Collection<GrantedAuthority> toGrantedAuthorities(Collection<String> roles) {
        return roles
                   .stream()
                   .map(role -> new GrantedAuthority() {
                                    @Override
                                    public String getAuthority() {
                                        return role;
                                    }
                                })
                   .collect(Collectors.toSet());
    }
}
