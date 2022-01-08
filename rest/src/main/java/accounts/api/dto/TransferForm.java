package accounts.api.dto;

import java.math.BigDecimal;

public record TransferForm(Long fromAccount, Long toAccount, BigDecimal amount) {}
