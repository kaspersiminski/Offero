package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.ports.incoming.AccountCreatable;

public class AccountCreator implements AccountCreatable {
    @Override
    public AccountDTO create(AccountCreateCommand accountCreateCommand) {
        return null;
    }
}
