package com.simek.offero.core.account;

import com.simek.offero.core.account.ports.outgoing.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AccountFacadeConfig {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountFacadeConfig(AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static AccountFacade inMemFacade() {
        final AccountCreator accountCreatable = new AccountCreator();
        final AccountDeleter accountDeletable = new AccountDeleter();
        final AccountFinderByEmail accountFinderByEmail = new AccountFinderByEmail();
        final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail = new SpringSecurityUserFinderByEmail();
        return new AccountFacade(accountCreatable, accountDeletable,accountFinderByEmail, springSecurityUserFinderByEmail, new InMemoryAccountRepository(), new BCryptPasswordEncoder());
    }

    @Bean
    public AccountFacade configure(){
        final AccountCreator accountCreatable = new AccountCreator();
        final AccountDeleter accountDeletable = new AccountDeleter();
        final AccountFinderByEmail accountFinderByEmail = new AccountFinderByEmail();
        final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail = new SpringSecurityUserFinderByEmail();
        return new AccountFacade(accountCreatable, accountDeletable, accountFinderByEmail, springSecurityUserFinderByEmail, accountRepository, bCryptPasswordEncoder);
    }
}
