package com.simek.offero.account.ports.incoming;

import com.simek.offero.account.dto.AccountDTO;
import lombok.Getter;

public interface AccountCreatable {
    AccountDTO create(AccountCreateCommand accountCreateCommand);

    @Getter
    class AccountCreateCommand {
        private final String email;
        private final String firstName;
        private final String lastName;

        public AccountCreateCommand(String email, String firstName, String lastName) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
