package com.simek.offero.account.model;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class EmailAddress {
    public static final String REGEX = "";
    public static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(name = "email")
    private String email;

    public EmailAddress(String email) {
        Assert.isTrue(isValid(email), "Invalid email address");
        this.email = email;
    }

    protected EmailAddress() {
    }

    public boolean isValid(String email) {
        return PATTERN.matcher(email)
                .matches();
    }

    public String getValue() {
        return email;
    }
}
