package com.simek.offero.account.model;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Getter
public class AccountId implements Serializable {
    public AccountId(long id) {
        this.id = id;
    }

    protected AccountId() {
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
