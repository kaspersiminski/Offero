package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.exceptions.EmailAddressDoesntExist;
import com.simek.offero.account.model.Account;
import com.simek.offero.account.model.EmailAddress;
import com.simek.offero.account.ports.incoming.AccountFindableByEmail;
import com.simek.offero.account.ports.outgoing.AccountRepository;

public class AccountFinderByEmail {

    AccountDTO findByEmail(AccountFindableByEmail.FindByEmailCommand findByEmailCommand, AccountRepository accountRepository) {
        EmailAddress emailAddress = new EmailAddress(findByEmailCommand.getEmail());

        Account account = accountRepository.findAccountByEmailEquals(emailAddress)
                .orElseThrow(EmailAddressDoesntExist::new);

        return AccountEntityDTOConverter.toDTO(account);
    }

}
