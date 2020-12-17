package com.simek.offero.application.controlers;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.ports.incoming.UserCreatable;
import com.simek.offero.core.user.ports.incoming.UserFindableByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserController(UserCreatable userCreatable, UserFindableByEmail userFindableByEmail) {
        this.userCreatable = userCreatable;
        this.userFindableByEmail = userFindableByEmail;
    }

    private final UserCreatable userCreatable;
    private final UserFindableByEmail userFindableByEmail;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserCreatable.CreateCommand command) {
        return ResponseEntity.ok(userCreatable.create(command));
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getLoggedInUserInfo(Principal principal) {
        final String username = principal.getName();
        final UserFindableByEmail.FindByEmailCommand command = new UserFindableByEmail.FindByEmailCommand(username);
        return ResponseEntity.ok(userFindableByEmail.findByEmail(command));
    }
}
