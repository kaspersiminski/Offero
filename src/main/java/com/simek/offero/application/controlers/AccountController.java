package com.simek.offero.application.controlers;

import com.simek.offero.core.account.dto.AccountDTO;
import com.simek.offero.core.account.ports.incoming.AccountCreatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountController(AccountCreatable accountCreatable) {
        this.accountCreatable = accountCreatable;
    }

    private final AccountCreatable accountCreatable;

    @PostMapping("")
    public ResponseEntity<AccountDTO> addNewAccount(@RequestBody AccountCreatable.AccountCreateCommand command) {
        return ResponseEntity.ok(accountCreatable.create(command));
    }

}
