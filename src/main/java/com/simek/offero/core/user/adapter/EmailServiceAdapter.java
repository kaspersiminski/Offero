package com.simek.offero.core.user.adapter;

import com.simek.offero.core.user.ports.outgoing.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailServiceAdapter implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceAdapter.class);

    @Override
    public void sendConfirmAccountNotification(String email, String confirmToken) {
        LOG.info("Sending confirmation email to: {} with token {}", email, confirmToken);
    }
}
