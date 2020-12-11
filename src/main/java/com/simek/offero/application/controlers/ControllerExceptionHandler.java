package com.simek.offero.application.controlers;

import com.simek.offero.core.account.exceptions.EmailAddressAlreadyUsed;
import com.simek.offero.core.account.exceptions.EmailAddressDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailAddressAlreadyUsed.class)
    public ResponseEntity<ErrorResponse> handleEmailAddressAlreadyUsed() {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .message("This email is already used.")
                        .build()
                );
    }

    @ExceptionHandler(EmailAddressDoesntExist.class)
    public ResponseEntity<ErrorResponse> handleEmailAddressDoesntExist() {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .message("Email not found.")
                        .build()
                );
    }


}
