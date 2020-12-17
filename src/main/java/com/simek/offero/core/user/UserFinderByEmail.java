package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.exceptions.EmailAddressDoesntExist;
import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.ports.incoming.UserFindableByEmail;
import com.simek.offero.core.user.ports.outgoing.UserRepository;

class UserFinderByEmail {

    private final UserRepository userRepository;

    UserFinderByEmail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserDTO findByEmail(UserFindableByEmail.FindByEmailCommand findByEmailCommand) {
        EmailAddress emailAddress = new EmailAddress(findByEmailCommand.getEmail());

        User user = userRepository.findUserByEmailEquals(emailAddress)
                .orElseThrow(EmailAddressDoesntExist::new);

        return UserEntityDTOConverter.toUserDTO(user);
    }

}
