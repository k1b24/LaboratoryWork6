package kib.lab6.common.util;


import kib.lab6.common.abstractions.AbstractMessage;

import java.io.PrintStream;

public class TextSender {

    private static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN
    private static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED
    private static final String ANSI_RESET = "\u001B[0m";
    private static PrintStream printStream;

    public TextSender(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printText(String message) {
        printStream.println(MESSAGE_COLOR + message + ANSI_RESET);
    }

    public void printError(String message) {
        printStream.println(ERROR_COLOR + message + ANSI_RESET);
    }

    public void printMessage(AbstractMessage message) {
        printStream.println(message.getMessage() + ANSI_RESET);
    }

    public void changePrintStream(PrintStream newPrintStream) {
        printStream = newPrintStream;
    }
}
