package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.dto.SpringSecurityUserDTO;
import com.simek.offero.core.user.model.User;

class UserEntityDTOConverter {
    static UserDTO toUserDTO(User entity) {
        return UserDTO.builder()
                .email(entity.getEmail().getValue())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

    static SpringSecurityUserDTO toSpringSecurityUserDTO(User entity) {
        return SpringSecurityUserDTO.builder()
                .username(entity.getEmail().getValue())
                .password(entity.getPassword())
                .build();
    }


}
