package accounts.prd;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "accounts.hom",
        "accounts.frame",
        // backservice repository & db hom
        "accounts.repository",
        "accounts.db.prd",
        // core module
        "accounts.application",
})
public class Build4 {
}
