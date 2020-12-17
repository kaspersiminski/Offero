package com.simek.offero.core.user.ports.outgoing;

public interface EmailService {
    void sendConfirmAccountNotification(String email, String confirmToken);
}
