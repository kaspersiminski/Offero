package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.exceptions.EmailAddressAlreadyUsed;
import com.simek.offero.core.account.model.Account;
import com.simek.offero.core.account.model.EmailAddress;
import com.simek.offero.core.account.ports.incoming.AccountCreatable;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AccountCreator {

    AccountDTO create(AccountCreatable.AccountCreateCommand command, AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        EmailAddress email = new EmailAddress(command.getEmail());

        if(accountRepository.findAccountByEmailEquals(email).isPresent()) {
            throw new EmailAddressAlreadyUsed();
        }

        Account account = Account.builder()
                .email(email)
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .password(bCryptPasswordEncoder.encode(command.getPassword()))
                .build();

        account = accountRepository.save(account);

        return AccountEntityDTOConverter.toAccountDTO(account);
    }
}
