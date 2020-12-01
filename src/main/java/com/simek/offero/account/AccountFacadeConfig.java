package com.simek.offero.account;

import com.simek.offero.account.ports.incoming.AccountCreatable;
import com.simek.offero.account.ports.incoming.AccountDeletable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountFacadeConfig {
    
    @Bean
    public AccountFacade configure(){
        final AccountCreatable accountCreatable = new AccountCreator();
        final AccountDeletable accountDeletable = new AccountDeleter();
        return new AccountFacade(accountCreatable, accountDeletable);
    }
}
