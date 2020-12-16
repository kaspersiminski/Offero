package com.simek.offero.core.account.model;

import com.simek.offero.infrastructure.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account extends AbstractEntity {

    @Embedded
    @Column(unique = true, nullable = false)
    private EmailAddress email;

    @Column(length = 40, nullable = false)
    private String firstName;

    @Column(length = 40, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;
}
