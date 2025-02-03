package kiddo.kiddomanager.exceptions;

public class ParentsNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Cannot find parents account with email %s";
    public ParentsNotFoundException(String email) {
        super(String.format(MESSAGE_FORMAT, email));
    }
}
