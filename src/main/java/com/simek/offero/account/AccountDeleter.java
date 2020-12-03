package com.simek.offero.account;

import com.simek.offero.account.exceptions.EmailAddressDoesntExist;
import com.simek.offero.account.model.Account;
import com.simek.offero.account.model.EmailAddress;
import com.simek.offero.account.ports.incoming.AccountDeletable;
import com.simek.offero.account.ports.outgoing.AccountRepository;

class AccountDeleter {

    public void delete(AccountDeletable.AccountDeleteCommand accountDeleteCommand, AccountRepository accountRepository) {
        EmailAddress email = new EmailAddress(accountDeleteCommand.getEmail());

        Account account = accountRepository.findAccountByEmailEquals(email)
                .orElseThrow(EmailAddressDoesntExist::new);

        accountRepository.delete(account);
    }

}
