package com.simek.offero.infrastructure.security;

import com.simek.offero.core.user.dto.SpringSecurityUserDTO;
import com.simek.offero.core.user.ports.incoming.SpringSecurityUserFindableByEmail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {


    final SpringSecurityUserFindableByEmail springSecurityUserFindableByEmail;

    public UserDetailsServiceImpl(SpringSecurityUserFindableByEmail springSecurityUserFindableByEmail) {
        this.springSecurityUserFindableByEmail = springSecurityUserFindableByEmail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SpringSecurityUserDTO user = springSecurityUserFindableByEmail.findByEmail(new SpringSecurityUserFindableByEmail.FindByEmailCommand(username));
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with name '" + username + "'");
        }
        return user;
    }
}
