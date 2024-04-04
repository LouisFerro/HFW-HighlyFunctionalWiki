package wiki.hf.service.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private UserAlreadyExistsException(String message) {
        super(message);
    }

    public static UserAlreadyExistsException Username(String username) {
        return new UserAlreadyExistsException("The username %s already exists.".formatted(username));
    }
}
