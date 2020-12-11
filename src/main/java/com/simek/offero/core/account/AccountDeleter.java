package com.simek.offero.core.account;

import com.simek.offero.core.account.exceptions.EmailAddressDoesntExist;
import com.simek.offero.core.account.model.Account;
import com.simek.offero.core.account.model.EmailAddress;
import com.simek.offero.core.account.ports.incoming.AccountDeletable;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;

class AccountDeleter {

    void delete(AccountDeletable.AccountDeleteCommand accountDeleteCommand, AccountRepository accountRepository) {
        EmailAddress email = new EmailAddress(accountDeleteCommand.getEmail());

        Account account = accountRepository.findAccountByEmailEquals(email)
                .orElseThrow(EmailAddressDoesntExist::new);

        accountRepository.delete(account);
    }

}
