package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.exceptions.EmailAddressAlreadyUsed;
import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.model.UserStatus;
import com.simek.offero.core.user.ports.incoming.UserCreatable;
import com.simek.offero.core.user.ports.outgoing.EmailService;
import com.simek.offero.core.user.ports.outgoing.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserCreator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    UserCreator(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    UserDTO create(UserCreatable.CreateCommand command) {
        EmailAddress email = new EmailAddress(command.getEmail());

        if(userRepository.findUserByEmailEquals(email).isPresent()) {
            throw new EmailAddressAlreadyUsed();
        }

        User user = User.builder()
                .email(email)
                .userStatus(UserStatus.WAIT_FOR_EMAIL_CONFIRMATION)
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .password(bCryptPasswordEncoder.encode(command.getPassword()))
                .build();

        user.generateConfirmationToken();
        user = userRepository.save(user);

        emailService.sendConfirmAccountNotification(user.getEmail().getValue(), user.getConfirmToken().toString());

        return UserEntityDTOConverter.toUserDTO(user);
    }
}
