package command;

public class NotEnoughArgsProvidedException extends RuntimeException {
    public NotEnoughArgsProvidedException(String message) {
        super(message);
    }

    public NotEnoughArgsProvidedException(int expected, int provided) {
        this(String.format("Expected %d args, provided %d", expected, provided));
    }
}
