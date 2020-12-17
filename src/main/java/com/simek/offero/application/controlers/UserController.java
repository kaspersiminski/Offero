package com.simek.offero.application.controlers;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.ports.incoming.UserCreatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserController(UserCreatable userCreatable) {
        this.userCreatable = userCreatable;
    }

    private final UserCreatable userCreatable;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody UserCreatable.CreateCommand command) {
        return ResponseEntity.ok(userCreatable.create(command));
    }

}
