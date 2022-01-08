package accounts.application.domain.model;

import java.util.function.Supplier;

public class Error {

    public static void mandatory(String name) {
        throw new BusinessException(name + " is mandatory");
    }

    public static void unexisting(String name) {
        throw new BusinessException(name + " do not exist");
    }

    public static void insuficientFunds() {
        throw new BusinessException("insuficient funds");
    }

    public static void sameAccount() {
        throw new BusinessException("both accounts are the same");
    }

    public static Supplier<BusinessException> unexistingSupplier(String name) {
        return () -> new BusinessException(name + " do not exist");
    }

    public static Supplier<BusinessException> mandatorySupplier(String name) {
        return () -> new BusinessException(name + " is mandatory");
    }

    public static Supplier<BusinessException> noId(String name) {
        return () -> new BusinessException(name + " has no Identifier");
    }
}
