package com.simek.offero.account;

import com.simek.offero.account.ports.outgoing.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AccountFacadeConfig {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountFacadeConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Bean
    public AccountFacade configure(){
        final AccountCreator accountCreatable = new AccountCreator();
        final AccountDeleter accountDeletable = new AccountDeleter();
        return new AccountFacade(accountCreatable, accountDeletable, accountRepository);
    }
}
