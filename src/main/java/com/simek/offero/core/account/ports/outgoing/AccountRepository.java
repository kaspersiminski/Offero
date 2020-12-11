package com.simek.offero.core.account.ports.outgoing;

import com.simek.offero.core.account.model.Account;
import com.simek.offero.core.account.model.EmailAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findAccountByEmailEquals(EmailAddress email);
}
