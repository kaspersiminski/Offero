package com.simek.offero.core.user;

import com.simek.offero.core.user.ports.outgoing.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserFacadeConfig {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserFacadeConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static UserFacade inMemFacade() {
        final UserCreator userCreator = new UserCreator();
        final UserDeleter userDeleter = new UserDeleter();
        final UserFinderByEmail userFinderByEmail = new UserFinderByEmail();
        final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail = new SpringSecurityUserFinderByEmail();
        return new UserFacade(userCreator, userDeleter, userFinderByEmail, springSecurityUserFinderByEmail, new InMemoryUserRepository(), new BCryptPasswordEncoder());
    }

    @Bean
    public UserFacade configure(){
        final UserCreator userCreator = new UserCreator();
        final UserDeleter userDeleter = new UserDeleter();
        final UserFinderByEmail userFinderByEmail = new UserFinderByEmail();
        final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail = new SpringSecurityUserFinderByEmail();
        return new UserFacade(userCreator, userDeleter, userFinderByEmail, springSecurityUserFinderByEmail, userRepository, bCryptPasswordEncoder);
    }
}
