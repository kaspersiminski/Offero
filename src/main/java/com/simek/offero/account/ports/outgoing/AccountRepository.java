package com.simek.offero.account.ports.outgoing;

import com.simek.offero.account.model.Account;
import com.simek.offero.account.model.AccountId;
import com.simek.offero.account.model.EmailAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, AccountId> {
    Optional<Account> findAccountByEmailEquals(EmailAddress email);
}
