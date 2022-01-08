package accounts.repository;

import accounts.application.domain.model.Account;
import accounts.application.domain.model.BusinessException;
import accounts.application.ports.out.AccountRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

import static java.util.Objects.isNull;

@Named
public class AccountRepositoryImpl implements AccountRepository {

    private static final String ERRO = "FATAL ERROR";
    private final JdbcTemplate jdbc;

    @Inject
    public AccountRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Account> findByNumber(Long accountNumber) {
        if ( accountNumber == null ) return Optional.empty();
        var sql = "select * from account where number = ?";
        RowMapper<Account> orm = (rs, nm) ->
                new Account(
                        rs.getLong(1),
                        rs.getBigDecimal(2),
                        rs.getString(3)
                );
        try {
            return jdbc.queryForStream(sql, orm, accountNumber).findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void update(Account account) {
        if ( isNull(account) ) throw new BusinessException(ERRO);
        var accountNumber = account.getNumber().orElseThrow(() -> new BusinessException(ERRO));
        var sql = "update account set balance = ?, owner = ? where number = ?";
        try {
            jdbc.update(sql, account.getBalance(), account.getOwner(), accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ERRO);
        }
    }
}
