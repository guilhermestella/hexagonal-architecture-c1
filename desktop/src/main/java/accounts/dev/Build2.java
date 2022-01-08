package accounts.dev;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "accounts.dev",
        "accounts.frame",
        // fake repository
        "accounts.adapter",
        // core module
        "accounts.application",
})
public class Build2 {
}
