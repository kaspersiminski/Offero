package com.simek.offero.account.ports.incoming;

import com.simek.offero.account.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AccountDeletable {
    AccountDTO delete(AccountDeleteCommand accountDeleteCommand);

    @AllArgsConstructor
    @Getter
    class AccountDeleteCommand {
        private String email;
    }
}
