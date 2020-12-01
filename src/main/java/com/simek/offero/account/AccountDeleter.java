package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.ports.incoming.AccountDeletable;

public class AccountDeleter implements AccountDeletable {
    @Override
    public AccountDTO delete(AccountDeleteCommand accountDeleteCommand) {
        return null;
    }
}
