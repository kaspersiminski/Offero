package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.ports.incoming.AccountCreatable;
import com.simek.offero.core.account.ports.incoming.AccountDeletable;
import com.simek.offero.core.account.ports.incoming.AccountFindableByEmail;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;

class AccountFacade implements AccountCreatable, AccountDeletable, AccountFindableByEmail {
    private final AccountCreator accountCreator;
    private final AccountDeleter accountDeleter;
    private final AccountRepository accountRepository;
    private final AccountFinderByEmail accountFinderByEmail;


    public AccountFacade(AccountCreator accountCreatable, AccountDeleter accountDeletable, AccountFinderByEmail accountFinderByEmail, AccountRepository accountRepository) {
        this.accountCreator = accountCreatable;
        this.accountDeleter = accountDeletable;
        this.accountRepository = accountRepository;
        this.accountFinderByEmail = accountFinderByEmail;
    }

    @Override
    public AccountDTO create(AccountCreateCommand accountCreateCommand) {
        return accountCreator.create(accountCreateCommand, accountRepository);
    }

    @Override
    public void delete(AccountDeleteCommand accountDeleteCommand) {
        accountDeleter.delete(accountDeleteCommand, accountRepository);
    }

    @Override
    public AccountDTO findByEmail(FindByEmailCommand findByEmailCommand) {
        return accountFinderByEmail.findByEmail(findByEmailCommand, accountRepository);
    }
}
