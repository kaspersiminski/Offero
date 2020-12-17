package com.simek.offero.core.user.ports.incoming;

import com.simek.offero.core.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserCreatable {
    UserDTO create(CreateCommand createCommand);

    @AllArgsConstructor
    @Getter
    class CreateCommand {
        private final String email;
        private final String firstName;
        private final String lastName;
        private final String password;
    }
}
