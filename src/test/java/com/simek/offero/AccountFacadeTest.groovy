package com.simek.offero

import com.simek.offero.account.AccountFacadeConfig
import com.simek.offero.account.dto.AccountDTO
import com.simek.offero.account.ports.incoming.AccountCreatable
import com.simek.offero.account.ports.incoming.AccountFindableByEmail
import spock.lang.Specification

class AccountFacadeTest extends Specification {
    def "After add account should be crated"() {
        given:
        final facade = AccountFacadeConfig.inMemFacade()
        final createCommand = new AccountCreatable.AccountCreateCommand("test@test.pl", "FirstName", "LastName")

        when:
        AccountDTO result = facade.create(createCommand)

        then:
        facade.findByEmail(new AccountFindableByEmail.FindByEmailCommand("test@test.pl")).email == "test@test.pl"
    }
}
