package util;

public class MissingBookNameException extends RuntimeException {
    public MissingBookNameException() {
        super("Book name is required.");
    }
}
