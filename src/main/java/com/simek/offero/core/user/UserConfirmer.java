package com.simek.offero.core.user;

import com.simek.offero.core.user.exceptions.EmailAddressDoesntExist;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.ports.incoming.UserConfirmable;
import com.simek.offero.core.user.ports.outgoing.UserRepository;

class UserConfirmer {

    private final UserRepository userRepository;

    UserConfirmer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void confirm(UserConfirmable.ConfirmCommand command) {
        EmailAddress email = new EmailAddress(command.getEmail());

        final User user = userRepository.findUserByEmailEquals(email)
                .orElseThrow(EmailAddressDoesntExist::new);

        if(user.getConfirmToken().validateAgainstGivenToken(command.getToken())) {
            user.confirmAccount();
        }

        userRepository.save(user);
    }
}
