package accounts.hom;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "accounts.hom",
        "accounts.frame",
        // backservice repository & db hom
        "accounts.repository",
        "accounts.db.hom",
        // core module
        "accounts.application",
})
public class Build3 {
}
