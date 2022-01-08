package accounts.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "accounts.api.resource",
        "accounts.adapter",
        "accounts.application.domain.service",
        "accounts.application.service",
})
public class RestTestConfig {
}
