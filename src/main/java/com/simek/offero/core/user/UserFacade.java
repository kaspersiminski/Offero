package com.simek.offero.core.user;

import com.simek.offero.core.user.dto.SpringSecurityUserDTO;
import com.simek.offero.core.user.dto.UserDTO;
import com.simek.offero.core.user.ports.incoming.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class UserFacade implements UserCreatable, UserDeletable, UserFindableByEmail, SpringSecurityUserFindableByEmail, UserConfirmable {
    private final UserCreator userCreator;
    private final UserDeleter userDeleter;
    private final SpringSecurityUserFinderByEmail springSecurityUserFinderByEmail;
    private final UserFinderByEmail userFinderByEmail;
    private final UserConfirmer userConfirmer;

    @Override
    public UserDTO create(CreateCommand createCommand) {
        return userCreator.create(createCommand);
    }

    @Override
    public void delete(DeleteCommand deleteCommand) {
        userDeleter.delete(deleteCommand);
    }

    @Override
    public UserDTO findByEmail(UserFindableByEmail.FindByEmailCommand findByEmailCommand) {
        return userFinderByEmail.findByEmail(findByEmailCommand);
    }

    @Override
    public SpringSecurityUserDTO findByEmail(SpringSecurityUserFindableByEmail.FindByEmailCommand findByEmailCommand) {
        return springSecurityUserFinderByEmail.findByEmail(findByEmailCommand);
    }

    @Override
    public void confirmUser(ConfirmCommand confirmationEmailCommand) {
        userConfirmer.confirm(confirmationEmailCommand);
    }
}
