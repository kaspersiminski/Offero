package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.dto.SpringSecurityUserDTO;
import com.simek.offero.core.user.ports.incoming.UserCreatable;
import com.simek.offero.core.user.ports.incoming.UserDeletable;
import com.simek.offero.core.user.ports.incoming.UserFindableByEmail;
import com.simek.offero.core.user.ports.incoming.SpringSecurityUserFindableByEmail;
import com.simek.offero.core.user.ports.outgoing.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserFacade implements UserCreatable, UserDeletable, UserFindableByEmail, SpringSecurityUserFindableByEmail {
    private final UserCreator userCreator;
    private final UserDeleter userDeleter;
    private final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail;
    private final UserRepository userRepository;
    private final UserFinderByEmail userFinderByEmail;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserFacade(UserCreator userCreator, UserDeleter userDeleter, UserFinderByEmail userFinderByEmail, SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userCreator = userCreator;
        this.userDeleter = userDeleter;
        this.springSecurityUserFinderByEmail = springSecurityUserFinderByEmail;
        this.userRepository = userRepository;
        this.userFinderByEmail = userFinderByEmail;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO create(CreateCommand createCommand) {
        return userCreator.create(createCommand, userRepository, bCryptPasswordEncoder);
    }

    @Override
    public void delete(DeleteCommand deleteCommand) {
        userDeleter.delete(deleteCommand, userRepository);
    }

    @Override
    public UserDTO findByEmail(UserFindableByEmail.FindByEmailCommand findByEmailCommand) {
        return userFinderByEmail.findByEmail(findByEmailCommand, userRepository);
    }

    @Override
    public SpringSecurityUserDTO findByEmail(SpringSecurityUserFindableByEmail.FindByEmailCommand findByEmailCommand) {
        return springSecurityUserFinderByEmail.findByEmail(findByEmailCommand, userRepository);
    }
}
