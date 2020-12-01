package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.ports.incoming.AccountCreatable;
import com.simek.offero.account.ports.incoming.AccountDeletable;

public class AccountFacade implements AccountCreatable, AccountDeletable {
    AccountCreatable accountCreatable;
    AccountDeletable accountDeletable;

    public AccountFacade(AccountCreatable accountCreatable, AccountDeletable accountDeletable) {
        this.accountCreatable = accountCreatable;
        this.accountDeletable = accountDeletable;
    }

    @Override
    public AccountDTO create(AccountCreateCommand accountCreateCommand) {
        return accountCreatable.create(accountCreateCommand);
    }

    @Override
    public AccountDTO delete(AccountDeleteCommand accountDeleteCommand) {
        return accountDeletable.delete(accountDeleteCommand);
    }
}
