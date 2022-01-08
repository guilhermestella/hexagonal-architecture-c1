package accounts.application;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Core Tests")
@SelectPackages({
        "accounts.application.domain.model",
        "accounts.application.domain.service",
        "accounts.application.service"
})
public class SuitCore {
}
