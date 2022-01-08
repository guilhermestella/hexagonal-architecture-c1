package accounts.api.resource;

import accounts.api.config.RestTestConfig;
import accounts.api.dto.TransferForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {RestTestConfig.class})
@WebMvcTest(TransferResource.class)
class TransferResourceTest {

    public static final String TRANSFERS = "/transfers";

    @Autowired
    MockMvc mvc;

    Long fakeFromAccount = 1L;
    Long fakeToAccount = 2L;
    Long fakeNonExistingAccount = 999L;
    BigDecimal reallyBigValue = BigDecimal.valueOf(99999999);

    @Test
    @DisplayName("Transfer Success")
    void transfer() throws Exception {
        TransferForm form = new TransferForm(fakeFromAccount, fakeToAccount, BigDecimal.ONE);

        mvc
                .perform(MockMvcRequestBuilders
                        .post(TRANSFERS)
                        .content(new ObjectMapper().writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Transfer Returns 400 if accounts are the same")
    void transferFailIfAccountsAreTheSame() throws Exception {
        TransferForm form = new TransferForm(fakeFromAccount, fakeFromAccount, BigDecimal.ONE);

        mvc
                .perform(MockMvcRequestBuilders
                        .post(TRANSFERS)
                        .content(new ObjectMapper().writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Transfer Returns 400 any account dont exist")
    void transferFailIfAccountDontExist() throws Exception {
        TransferForm form = new TransferForm(fakeFromAccount, fakeNonExistingAccount, BigDecimal.ONE);

        mvc
                .perform(MockMvcRequestBuilders
                        .post(TRANSFERS)
                        .content(new ObjectMapper().writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Transfer Returns 400 if insulficient funds")
    void transferFailIfInsuficientFunds() throws Exception {
        TransferForm form = new TransferForm(fakeFromAccount, fakeToAccount, reallyBigValue);

        mvc
                .perform(MockMvcRequestBuilders
                        .post(TRANSFERS)
                        .content(new ObjectMapper().writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
