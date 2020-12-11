package com.simek.offero.core.account;

import com.simek.offero.core.account.ports.outgoing.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountFacadeConfig {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountFacadeConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static AccountFacade inMemFacade() {
        final AccountCreator accountCreatable = new AccountCreator();
        final AccountDeleter accountDeletable = new AccountDeleter();
        final AccountFinderByEmail accountFinderByEmail = new AccountFinderByEmail();
        return new AccountFacade(accountCreatable, accountDeletable,accountFinderByEmail , new InMemoryAccountRepository());
    }

    @Bean
    public AccountFacade configure(){
        final AccountCreator accountCreatable = new AccountCreator();
        final AccountDeleter accountDeletable = new AccountDeleter();
        final AccountFinderByEmail accountFinderByEmail = new AccountFinderByEmail();
        return new AccountFacade(accountCreatable, accountDeletable, accountFinderByEmail, accountRepository);
    }
}
