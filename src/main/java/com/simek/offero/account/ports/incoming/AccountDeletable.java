package com.simek.offero.account.ports.incoming;

import com.simek.offero.account.dto.AccountDTO;

public interface AccountDeletable {
    AccountDTO delete(AccountDeleteCommand accountDeleteCommand);

    class AccountDeleteCommand {
        private String email;
    }
}
