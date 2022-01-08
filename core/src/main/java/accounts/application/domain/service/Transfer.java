package accounts.application.domain.service;

import accounts.application.domain.model.Account;

import javax.inject.Named;
import java.math.BigDecimal;

import static accounts.application.domain.model.Error.mandatory;
import static accounts.application.domain.model.Error.sameAccount;
import static java.util.Objects.isNull;

@Named
public class Transfer {
    public void transfer(BigDecimal amount, Account from, Account to) {
        if ( isNull(amount) ) mandatory("amount");
        if ( isNull(from) ) mandatory("from");
        if ( isNull(to) ) mandatory("to");
        if ( from.equals(to) ) sameAccount();
        from.withdraw(amount);
        to.deposit(amount);
    }
}
