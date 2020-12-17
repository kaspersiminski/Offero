package com.simek.offero.core.user.ports.incoming;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UserDeletable {
    void delete(DeleteCommand deleteCommand);

    @AllArgsConstructor
    @Getter
    class DeleteCommand {
        private final String email;
    }
}
