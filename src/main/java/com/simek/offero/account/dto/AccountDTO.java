package com.simek.offero.account.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountDTO {
    private String email;
    private String firstName;
    private String lastName;
}
