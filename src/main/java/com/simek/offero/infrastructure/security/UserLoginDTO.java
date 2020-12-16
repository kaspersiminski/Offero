package com.simek.offero.infrastructure.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDTO {
    private String username;
    private String password;
}