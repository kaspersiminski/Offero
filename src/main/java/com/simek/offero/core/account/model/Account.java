package com.simek.offero.core.account.model;

import com.simek.offero.infrastructure.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account extends AbstractEntity {

    @Embedded
    @Column(unique = true)
    private EmailAddress email;

    @Column(length = 40)
    private String firstName;

    @Column(length = 40)
    private String lastName;
}
