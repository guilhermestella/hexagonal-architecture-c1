package accounts.application.service;

import accounts.application.domain.model.Account;
import accounts.application.domain.service.Transfer;
import accounts.application.ports.in.TransferService;
import accounts.application.ports.out.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Optional;

import static accounts.application.domain.model.Error.*;
import static java.util.Objects.isNull;

@Named
public class TransferServiceImpl implements TransferService {

    private final AccountRepository repository;
    private final Transfer transfer;

    @Inject
    public TransferServiceImpl(AccountRepository repository, Transfer transfer) {
        this.repository = repository;
        this.transfer = transfer;
    }

    @Override
    public Optional<Account> getAccount(Long accountNumber) {
        return repository.findByNumber(accountNumber);
    }

    @Override
    @Transactional
    public void transfer(Long fromAccountNumber, Long toAccountNumber, BigDecimal amount) {
        if ( isNull(fromAccountNumber) || isNull(toAccountNumber) || isNull(amount) ) mandatory("accounts and amount are mandatory");
        Account from = repository.findByNumber(fromAccountNumber).orElseThrow(unexistingSupplier("from Account"));
        Account to = repository.findByNumber(toAccountNumber).orElseThrow(unexistingSupplier("to Account"));
        transfer.transfer(amount, from, to);
        repository.update(from);
        repository.update(to);
    }
}
