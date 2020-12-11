package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.exceptions.EmailAddressAlreadyUsed;
import com.simek.offero.core.account.model.Account;
import com.simek.offero.core.account.model.EmailAddress;
import com.simek.offero.core.account.ports.incoming.AccountCreatable;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;

class AccountCreator {

    AccountDTO create(AccountCreatable.AccountCreateCommand command, AccountRepository accountRepository) {
        EmailAddress email = new EmailAddress(command.getEmail());

        if(accountRepository.findAccountByEmailEquals(email).isPresent()) {
            throw new EmailAddressAlreadyUsed();
        }

        Account account = Account.builder()
                .email(email)
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .build();

        account = accountRepository.save(account);

        return AccountEntityDTOConverter.toDTO(account);
    }
}
