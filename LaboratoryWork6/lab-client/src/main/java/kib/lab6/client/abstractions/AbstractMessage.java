package kib.lab6.client.abstractions;

public abstract class AbstractMessage {

    public static final String ANSI_RESET = "\u001B[0m"; //ANSI_RESET
    private final String message;

    public AbstractMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message + ANSI_RESET;
    }
}
