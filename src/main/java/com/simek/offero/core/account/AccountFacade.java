package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.dto.SpringSecurityUserDTO;
import com.simek.offero.core.account.ports.incoming.AccountCreatable;
import com.simek.offero.core.account.ports.incoming.AccountDeletable;
import com.simek.offero.core.account.ports.incoming.AccountFindableByEmail;
import com.simek.offero.core.account.ports.incoming.SpringSecurityUserFindableByEmail;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AccountFacade implements AccountCreatable, AccountDeletable, AccountFindableByEmail, SpringSecurityUserFindableByEmail {
    private final AccountCreator accountCreator;
    private final AccountDeleter accountDeleter;
    private final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail;
    private final AccountRepository accountRepository;
    private final AccountFinderByEmail accountFinderByEmail;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AccountFacade(AccountCreator accountCreatable, AccountDeleter accountDeletable, AccountFinderByEmail accountFinderByEmail, SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail, AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountCreator = accountCreatable;
        this.accountDeleter = accountDeletable;
        this.springSecurityUserFinderByEmail = springSecurityUserFinderByEmail;
        this.accountRepository = accountRepository;
        this.accountFinderByEmail = accountFinderByEmail;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AccountDTO create(AccountCreateCommand accountCreateCommand) {
        return accountCreator.create(accountCreateCommand, accountRepository, bCryptPasswordEncoder);
    }

    @Override
    public void delete(AccountDeleteCommand accountDeleteCommand) {
        accountDeleter.delete(accountDeleteCommand, accountRepository);
    }

    @Override
    public AccountDTO findByEmail(AccountFindableByEmail.FindByEmailCommand findByEmailCommand) {
        return accountFinderByEmail.findByEmail(findByEmailCommand, accountRepository);
    }

    @Override
    public SpringSecurityUserDTO findByEmail(SpringSecurityUserFindableByEmail.FindByEmailCommand findByEmailCommand) {
        return springSecurityUserFinderByEmail.findByEmail(findByEmailCommand, accountRepository);
    }
}
