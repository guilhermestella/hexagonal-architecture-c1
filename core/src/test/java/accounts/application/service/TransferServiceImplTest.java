package accounts.application.service;

import accounts.application.domain.model.Account;
import accounts.application.domain.model.BusinessException;
import accounts.application.ports.in.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = Build1.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Use Case: Transfer")
public class TransferServiceImplTest {

    BigDecimal bigValue = new BigDecimal(999999);
    BigDecimal five = new BigDecimal(5);

    Long fromAccountNumber = 1L;
    Long toAccountNumber = 2L;
    Long nonExistingAccountNumber = 0L;

    @Inject
    TransferService transferService;

    @Test
    void transferWithInvalidAccountThrowsException() {
        assertThrows(BusinessException.class, () -> transferService.transfer(null, null, bigValue));
        assertThrows(BusinessException.class, () -> transferService.transfer(fromAccountNumber, nonExistingAccountNumber, bigValue));
        assertThrows(BusinessException.class, () -> transferService.transfer(nonExistingAccountNumber, toAccountNumber, bigValue));
    }

    @Test
    void transferWithSameAccountThrowsException() {
        assertThrows(BusinessException.class, () -> transferService.transfer(fromAccountNumber, fromAccountNumber, five));
    }

    @Test
    void transferWithoutFundsThrowsException() {
        assertThrows(BusinessException.class, () -> transferService.transfer(fromAccountNumber, toAccountNumber, bigValue));
    }

    @Test
    void transfer() {
        // when
        transferService.transfer(fromAccountNumber, toAccountNumber, five);

        // then
        Account updatedFromAccount = transferService.getAccount(fromAccountNumber).orElseThrow();
        Account updatedToAccount = transferService.getAccount(toAccountNumber).orElseThrow();
        assertEquals(updatedFromAccount.getBalance(), BigDecimal.valueOf(5));
        assertEquals(updatedToAccount.getBalance(), BigDecimal.valueOf(105));
    }
}
