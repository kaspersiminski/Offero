package com.simek.offero.core.user.model;

import com.simek.offero.infrastructure.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends AbstractEntity {

    @Embedded
    @Column(unique = true, nullable = false)
    private EmailAddress email;

    @Column(length = 40, nullable = false)
    private String firstName;

    @Column(length = 40, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;
}
