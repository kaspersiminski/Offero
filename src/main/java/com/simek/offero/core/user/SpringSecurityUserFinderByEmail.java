package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.SpringSecurityUserDTO;
import com.simek.offero.core.user.exceptions.EmailAddressDoesntExist;
import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.ports.incoming.SpringSecurityUserFindableByEmail;
import com.simek.offero.core.user.ports.outgoing.UserRepository;

class SpringSecurityUserFinderByEmail {

    SpringSecurityUserDTO findByEmail(SpringSecurityUserFindableByEmail.FindByEmailCommand findByEmailCommand, UserRepository userRepository) {
        EmailAddress emailAddress = new EmailAddress(findByEmailCommand.getEmail());

        User user = userRepository.findUserByEmailEquals(emailAddress)
                .orElseThrow(EmailAddressDoesntExist::new);

        return UserEntityDTOConverter.toSpringSecurityUserDTO(user);
    }


}
