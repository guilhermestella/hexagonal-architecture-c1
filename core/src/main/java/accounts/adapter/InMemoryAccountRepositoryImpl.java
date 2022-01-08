package accounts.adapter;

import accounts.application.domain.model.Account;
import accounts.application.ports.out.AccountRepository;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static accounts.application.domain.model.Error.noId;
import static accounts.application.domain.model.Error.unexistingSupplier;

@Named
public class InMemoryAccountRepositoryImpl implements AccountRepository {

    private final Map<Long, Account> inMemoryDatabase = new HashMap<>();

    public InMemoryAccountRepositoryImpl() {
        inMemoryDatabase.put(1L, new Account(1L, BigDecimal.valueOf(10), "John"));
        inMemoryDatabase.put(2L, new Account(2L, BigDecimal.valueOf(100), "Bob"));
        inMemoryDatabase.put(3L, new Account(3L, BigDecimal.valueOf(50), "Annie"));
        inMemoryDatabase.put(4L, new Account(4L, BigDecimal.valueOf(25), "Lucy"));
    }

    @Override
    public Optional<Account> findByNumber(Long number) {
        System.out.println("Fake findByNumber: " + number);
        return Optional.ofNullable(inMemoryDatabase.get(number));
    }

    @Override
    public void update(Account account) {
        System.out.println("Fake update: " + account.getNumber().orElse(null));
        this
                .findByNumber(
                        account.getNumber()
                                .orElseThrow(noId("account")))
                .orElseThrow(unexistingSupplier("account: " + account.getNumber()));
        inMemoryDatabase.put(account.getNumber().get(), account);
    }
}
