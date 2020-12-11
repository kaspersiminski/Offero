package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.model.Account;

class AccountEntityDTOConverter {
    static AccountDTO toDTO(Account entity) {
        return AccountDTO.builder()
                .email(entity.getEmail().getValue())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
