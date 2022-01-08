package accounts.application.ports.in;

import accounts.application.domain.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface TransferService {
    Optional<Account> getAccount(Long number);
    void transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount);
}
