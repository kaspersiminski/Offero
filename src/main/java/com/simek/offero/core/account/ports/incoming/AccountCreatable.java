package com.simek.offero.core.account.ports.incoming;

import com.simek.offero.core.account.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AccountCreatable {
    AccountDTO create(AccountCreateCommand accountCreateCommand);

    @AllArgsConstructor
    @Getter
    class AccountCreateCommand {
        private final String email;
        private final String firstName;
        private final String lastName;
    }
}
