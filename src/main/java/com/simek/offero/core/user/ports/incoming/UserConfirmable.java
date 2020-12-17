package com.simek.offero.core.user.ports.incoming;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserConfirmable {
    void confirmUser(ConfirmCommand confirmationEmailCommand);

    @AllArgsConstructor
    @Getter
    class ConfirmCommand {
        private final String email;
        private final String token;
    }

}
