package kib.lab6.common.util.console_workers;

import kib.lab6.common.abstractions.AbstractMessage;

public class SuccessMessage extends AbstractMessage {

    private static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN

    public SuccessMessage(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return MESSAGE_COLOR + super.getMessage();
    }
}
