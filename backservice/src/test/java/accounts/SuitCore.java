package accounts;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("BackService Tests")
@SelectPackages({
        "accounts.repository"
})
public class SuitCore { }
