package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.ports.incoming.AccountCreatable;
import com.simek.offero.account.ports.incoming.AccountDeletable;
import com.simek.offero.account.ports.outgoing.AccountRepository;

class AccountFacade implements AccountCreatable, AccountDeletable {
    private final AccountCreator accountCreator;
    private final AccountDeleter accountDeleter;
    private final AccountRepository accountRepository;


    public AccountFacade(AccountCreator accountCreatable, AccountDeleter accountDeletable, AccountRepository accountRepository) {
        this.accountCreator = accountCreatable;
        this.accountDeleter = accountDeletable;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO create(AccountCreateCommand accountCreateCommand) {
        return accountCreator.create(accountCreateCommand, accountRepository);
    }

    @Override
    public AccountDTO delete(AccountDeleteCommand accountDeleteCommand) {
        return accountDeleter.delete(accountDeleteCommand, accountRepository);
    }
}
