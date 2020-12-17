package com.simek.offero.core.user.ports.outgoing;

import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmailEquals(EmailAddress email);
}
