package accounts.application.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "accounts.adapter",
        "accounts.application.domain.service",
        "accounts.application.service",
})
public class Build1 {
}
