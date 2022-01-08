package accounts.application.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Model: Account")
class AccountTest {

    BigDecimal hundred = new BigDecimal(100);
    Account validAccount;

    @BeforeEach
    void setup() {
        validAccount = new Account(1L, hundred, "John");
    }

    @Test
    void depositWithInvalidAmountThrowsException() {
        assertThrows(BusinessException.class, () -> validAccount.deposit(null));
        assertThrows(BusinessException.class, () -> validAccount.deposit(BigDecimal.valueOf(-1)));
    }

    @Test
    void withdrawWithInvalidAmountThrowsException() {
        assertThrows(BusinessException.class, () -> validAccount.withdraw(null));
        assertThrows(BusinessException.class, () -> validAccount.withdraw(BigDecimal.valueOf(-1)));
    }

    @Test
    void deposit() {
        // given
        BigDecimal amount = BigDecimal.valueOf(30);

        // when
        validAccount.deposit(amount);

        // then
        assertEquals(BigDecimal.valueOf(130), validAccount.getBalance());
    }

    @Test
    void withdraw() {
        // given
        BigDecimal amount = BigDecimal.valueOf(30);

        // when
        validAccount.withdraw(amount);

        // then
        assertEquals(BigDecimal.valueOf(70), validAccount.getBalance());
    }

}
