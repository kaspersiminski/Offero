package com.simek.offero.core.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private final String email;
    private final String firstName;
    private final String lastName;
}
