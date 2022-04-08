package kib.lab6.common.util.console_workers;

import kib.lab6.common.abstractions.AbstractMessage;

public class ErrorMessage extends AbstractMessage {

    public static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED

    public ErrorMessage(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ERROR_COLOR + super.getMessage();
    }
}
