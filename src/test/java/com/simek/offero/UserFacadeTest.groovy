package com.simek.offero

import com.simek.offero.core.user.UserFacadeConfig
import com.simek.offero.core.user.ports.incoming.UserCreatable
import com.simek.offero.core.user.ports.incoming.UserFindableByEmail
import spock.lang.Specification

class UserFacadeTest extends Specification {
    def "After add account should be crated"() {
        given:
        final facade = UserFacadeConfig.inMemFacade()
        final createCommand = new UserCreatable.CreateCommand("test@test.pl", "FirstName", "LastName")

        when:
        facade.create(createCommand)

        then:
        facade.findByEmail(new UserFindableByEmail.FindByEmailCommand("test@test.pl")).email == "test@test.pl"
    }


    def "Should reject user with to long email"() {
        given:
        final facade = UserFacadeConfig.inMemFacade()
        final createCommand = new UserCreatable.CreateCommand("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx@test.pl", "FirstName", "LastName")

        when:
        facade.create(createCommand)

        then:
        thrown(IllegalArgumentException)
    }
}
