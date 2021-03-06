package com.simek.offero.core.user;

import com.simek.offero.core.user.exceptions.EmailAddressDoesntExist;
import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.ports.incoming.UserDeletable;
import com.simek.offero.core.user.ports.outgoing.UserRepository;

class UserDeleter {

    private final UserRepository userRepository;

    UserDeleter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void delete(UserDeletable.DeleteCommand deleteCommand) {
        EmailAddress email = new EmailAddress(deleteCommand.getEmail());

        User user = userRepository.findUserByEmailEquals(email)
                .orElseThrow(EmailAddressDoesntExist::new);

        userRepository.delete(user);
    }

}
