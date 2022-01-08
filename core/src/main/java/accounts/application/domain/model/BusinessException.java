package accounts.application.domain.model;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
