package com.simek.offero.core.user;

import com.simek.offero.core.user.adapter.EmailServiceAdapter;
import com.simek.offero.core.user.ports.outgoing.EmailService;
import com.simek.offero.core.user.ports.outgoing.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserFacadeConfig {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserFacadeConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = new EmailServiceAdapter();
    }

    @Bean
    public UserFacade configure(){
        final UserCreator userCreator = new UserCreator(userRepository, bCryptPasswordEncoder, emailService);
        final UserDeleter userDeleter = new UserDeleter(userRepository);
        final UserFinderByEmail userFinderByEmail = new UserFinderByEmail(userRepository);
        final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail = new SpringSecurityUserFinderByEmail(userRepository);
        final UserConfirmer userConfirmer = new UserConfirmer(userRepository);
        return new UserFacade(userCreator, userDeleter, springSecurityUserFinderByEmail, userFinderByEmail, userConfirmer);
    }

    public static UserFacade inMemFacade() {
        final InMemoryUserRepository userRepository = new InMemoryUserRepository();
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final UserCreator userCreator = new UserCreator(userRepository, bCryptPasswordEncoder, new EmailServiceAdapter());
        final UserDeleter userDeleter = new UserDeleter(userRepository);
        final UserFinderByEmail userFinderByEmail = new UserFinderByEmail(userRepository);
        final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail = new SpringSecurityUserFinderByEmail(userRepository);
        final UserConfirmer userConfirmer = new UserConfirmer(userRepository);
        return new UserFacade(userCreator, userDeleter, springSecurityUserFinderByEmail, userFinderByEmail, userConfirmer);
    }
}
