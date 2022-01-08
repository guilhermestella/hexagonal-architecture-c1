package accounts.application.domain.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static accounts.application.domain.model.Error.insuficientFunds;
import static accounts.application.domain.model.Error.mandatory;
import static java.util.Objects.isNull;

public class Account {

    private final Long number;
    private BigDecimal balance;
    private final String owner;

    public Account() {
        this.number = 0L;
        this.balance = BigDecimal.ZERO;
        this.owner = "not informed";
    }

    public Account(Long number, BigDecimal balance, String owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public void deposit(BigDecimal amount) throws BusinessException {
        if ( isNull(amount) ) mandatory("amount");
        if ( amount.compareTo(BigDecimal.ZERO) <= 0 ) mandatory("amount");
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) throws BusinessException {
        if ( isNull(amount) ) mandatory("amount");
        if ( amount.compareTo(BigDecimal.ZERO) <= 0 ) mandatory("amount");
        if ( amount.compareTo(balance) > 0 ) insuficientFunds();
        balance = balance.subtract(amount);
    }

    public Optional<Long> getNumber() {
        return Optional.ofNullable(number);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                ", owner='" + owner + '\'' +
                '}';
    }
}
