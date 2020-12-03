package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.exceptions.EmailAddressAlreadyUsed;
import com.simek.offero.account.model.Account;
import com.simek.offero.account.model.EmailAddress;
import com.simek.offero.account.ports.incoming.AccountCreatable;
import com.simek.offero.account.ports.outgoing.AccountRepository;

class AccountCreator {

    public AccountDTO create(AccountCreatable.AccountCreateCommand command, AccountRepository accountRepository) {
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
