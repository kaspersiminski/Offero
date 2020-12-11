package com.simek.offero.core.account.ports.incoming;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AccountDeletable {
    void delete(AccountDeleteCommand accountDeleteCommand);

    @AllArgsConstructor
    @Getter
    class AccountDeleteCommand {
        private final String email;
    }
}
