package com.simek.offero.account.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @EmbeddedId
    private AccountId accountId;

    @Embedded
    @Column(unique = true)
    private EmailAddress email;

    private String firstName;

    private String lastName;
}
