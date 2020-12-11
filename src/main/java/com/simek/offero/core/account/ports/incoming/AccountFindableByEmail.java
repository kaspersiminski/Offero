package com.simek.offero.core.account.ports.incoming;

import com.simek.offero.core.account.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AccountFindableByEmail {
    AccountDTO findByEmail(FindByEmailCommand findByEmailCommand);

    @AllArgsConstructor
    @Getter
    class FindByEmailCommand {
        private final String email;
    }
}
