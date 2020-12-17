package com.simek.offero.core.user.ports.incoming;

import com.simek.offero.core.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserFindableByEmail {
    UserDTO findByEmail(FindByEmailCommand findByEmailCommand);

    @AllArgsConstructor
    @Getter
    class FindByEmailCommand {
        private final String email;
    }
}
