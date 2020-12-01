package com.simek.offero.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @EmbeddedId
    private AccountId accountId;

    @Embedded
    @Column(unique = true)
    private EmailAddress email;

    private String firstName;

    private String lastName;
}
