package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.exceptions.EmailAddressDoesntExist;
import com.simek.offero.core.account.model.Account;
import com.simek.offero.core.account.model.EmailAddress;
import com.simek.offero.core.account.ports.incoming.AccountFindableByEmail;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;

class AccountFinderByEmail {

    AccountDTO findByEmail(AccountFindableByEmail.FindByEmailCommand findByEmailCommand, AccountRepository accountRepository) {
        EmailAddress emailAddress = new EmailAddress(findByEmailCommand.getEmail());

        Account account = accountRepository.findAccountByEmailEquals(emailAddress)
                .orElseThrow(EmailAddressDoesntExist::new);

        return AccountEntityDTOConverter.toDTO(account);
    }

}
