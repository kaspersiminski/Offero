package com.simek.offero.core.account;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.dto.SpringSecurityUserDTO;
import com.simek.offero.core.account.model.Account;

class AccountEntityDTOConverter {
    static AccountDTO toAccountDTO(Account entity) {
        return AccountDTO.builder()
                .email(entity.getEmail().getValue())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

    static SpringSecurityUserDTO toSpringSecurityUserDTO(Account entity) {
        return SpringSecurityUserDTO.builder()
                .username(entity.getEmail().getValue())
                .password(entity.getPassword())
                .build();
    }


}
