package com.simek.offero.account.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountDTO {
    private final String email;
    private final String firstName;
    private final String lastName;
}
