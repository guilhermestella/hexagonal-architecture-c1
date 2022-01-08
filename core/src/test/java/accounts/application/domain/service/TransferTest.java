package accounts.application.domain.service;

import accounts.application.domain.model.Account;
import accounts.application.domain.model.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Service: Account")
class TransferTest {

    BigDecimal hundred = new BigDecimal(100);
    Account from;
    Account to;

    @BeforeEach
    void setup() {
        from = new Account(1L, hundred, "John");
        to = new Account(2L, hundred, "Bob");
    }

    @Test
    void transfer() {
        // given
        BigDecimal amount = BigDecimal.valueOf(50);

        // when
        new Transfer().transfer(amount, from, to);

        // then
        assertEquals(BigDecimal.valueOf(50), from.getBalance());
        assertEquals(BigDecimal.valueOf(150), to.getBalance());
    }

    @Test
    void transferWithInvalidAmountThrowsException() {
        assertThrows(BusinessException.class, () -> new Transfer().transfer(null, from, to));
        assertThrows(BusinessException.class, () -> new Transfer().transfer(BigDecimal.valueOf(-1), from, to));
    }

    @Test
    void transferWithInsuficientFundsThrowsException() {
        // given
        BigDecimal amount = BigDecimal.valueOf(10000);

        // when & then
        assertThrows(BusinessException.class,
                () -> new Transfer().transfer(amount, from, to));
    }

}
