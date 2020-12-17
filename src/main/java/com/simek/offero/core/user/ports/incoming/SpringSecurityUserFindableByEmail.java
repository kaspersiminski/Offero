package com.simek.offero.core.user.ports.incoming;

import com.simek.offero.core.user.dto.SpringSecurityUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface SpringSecurityUserFindableByEmail {
    SpringSecurityUserDTO findByEmail(FindByEmailCommand findByEmailCommand);

    @AllArgsConstructor
    @Getter
    class FindByEmailCommand {
        private final String email;
    }
}
