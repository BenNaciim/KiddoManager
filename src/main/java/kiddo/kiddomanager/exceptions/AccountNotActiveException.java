package kiddo.kiddomanager.exceptions;

public class AccountNotActiveException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Your account is not active. please wait your account to be activated";
    public AccountNotActiveException() {
        super(String.format(MESSAGE_FORMAT));
    }
}
