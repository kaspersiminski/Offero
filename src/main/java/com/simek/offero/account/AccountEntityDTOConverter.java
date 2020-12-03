package com.simek.offero.account;

import com.simek.offero.account.dto.AccountDTO;
import com.simek.offero.account.model.Account;

class AccountEntityDTOConverter {
    public static AccountDTO toDTO(Account entity) {
        return AccountDTO.builder()
                .email(entity.getEmail().getValue())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
