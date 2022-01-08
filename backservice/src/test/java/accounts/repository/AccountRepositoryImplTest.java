package accounts.repository;

import accounts.application.domain.model.Account;
import accounts.application.domain.model.BusinessException;
import accounts.application.ports.out.AccountRepository;
import accounts.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository: Account")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class AccountRepositoryImplTest {

    public static final long FIRST_VALID_ACCOUNT_NUMBER = 10L;
    public static final long SECOND_VALID_ACCOUNT_NUMBER = 20L;
    public static final long INVALID_ACCOUNT_NUMBER = 9999L;

    @Inject
    AccountRepository repository;

    @Test
    @DisplayName("Find by Account Number")
    void findByNumber() {
        // given
        Long accountNumber = SECOND_VALID_ACCOUNT_NUMBER;

        // when
        Optional<Account> optAccount = repository.findByNumber(accountNumber);

        // then
        assertTrue(optAccount.isPresent());
        assertTrue(optAccount.get().getNumber().isPresent());
        assertEquals(accountNumber, optAccount.get().getNumber().get());
        assertThat(new BigDecimal(600), comparesEqualTo(optAccount.get().getBalance()));
        assertEquals("Thomas", optAccount.get().getOwner());
    }

    @Test
    @DisplayName("Find by Unexisting Account Number Returns Empty")
    void findByUnexistingNumberReturnsEmpty() {
        // given
        Long accountNumber = INVALID_ACCOUNT_NUMBER;

        // when
        Optional<Account> optAccount = repository.findByNumber(accountNumber);

        // then
        assertTrue(optAccount.isEmpty());
    }

    @Test
    @DisplayName("Update Account")
    void update() {
        // given
        Account account = repository.findByNumber(FIRST_VALID_ACCOUNT_NUMBER).orElseThrow();
        var deposit = BigDecimal.valueOf(100L);
        var currentBalance = account.getBalance();

        // when
        account.deposit(deposit);
        repository.update(account);

        // then
        Optional<Account> foundAccount = repository.findByNumber(FIRST_VALID_ACCOUNT_NUMBER);
        assertTrue(foundAccount.isPresent());
        assertThat(currentBalance.add(deposit), comparesEqualTo(foundAccount.get().getBalance()));
    }

    @Test
    @DisplayName("Update Account")
    void updateInvalidAccountThrowsException() {
        // given
        Account account = new Account(null, BigDecimal.ZERO, "Foo");

        // when
        Runnable update = () -> repository.update(account);

        // then
        assertThrows(BusinessException.class, update::run);
    }
}
