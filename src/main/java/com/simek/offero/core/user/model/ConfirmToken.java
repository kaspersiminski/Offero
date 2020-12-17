package com.simek.offero.core.user.model;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Embeddable
public class ConfirmToken {


    public ConfirmToken() {}

    public ConfirmToken(String email) {
        final ConfirmTokenPayload confirmTokenPayload = new ConfirmTokenPayload(email);

        confirmationToken = Base64.getEncoder()
                .encodeToString(confirmTokenPayload.toString()
                        .getBytes(StandardCharsets.UTF_8)
                );
    }

    @Column(name = "confirmation_token")
    private String confirmationToken;

    public boolean validateAgainstGivenToken(String givenToken) {
        if(StringUtils.isBlank(confirmationToken) || StringUtils.isBlank(givenToken)) {
            return false;
        }

        return confirmationToken.equals(givenToken);
    }


    @AllArgsConstructor
    private class ConfirmTokenPayload {
        private final String email;

        @Override
        public String toString() {
            return "{" +
                    "\"email\":\"" + email + "\"" +
                    '}';
        }
    }

    @Override
    public String toString() {
        return confirmationToken;
    }
}
