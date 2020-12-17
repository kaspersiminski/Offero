package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.exceptions.EmailAddressAlreadyUsed;
import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.ports.incoming.UserCreatable;
import com.simek.offero.core.user.ports.outgoing.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserCreator {

    UserDTO create(UserCreatable.CreateCommand command, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        EmailAddress email = new EmailAddress(command.getEmail());

        if(userRepository.findUserByEmailEquals(email).isPresent()) {
            throw new EmailAddressAlreadyUsed();
        }

        User user = User.builder()
                .email(email)
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .password(bCryptPasswordEncoder.encode(command.getPassword()))
                .build();

        user = userRepository.save(user);

        return UserEntityDTOConverter.toUserDTO(user);
    }
}
