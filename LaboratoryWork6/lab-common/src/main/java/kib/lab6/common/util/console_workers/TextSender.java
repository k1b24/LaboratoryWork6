package kib.lab6.common.util.console_workers;


import kib.lab6.common.abstractions.AbstractMessage;

import java.io.PrintStream;

public class TextSender {

    private static final String ANSI_RESET = "\u001B[0m";
    private static PrintStream printStream;

    public TextSender(PrintStream printStream) {
        TextSender.printStream = printStream;
    }

    public void printMessage(AbstractMessage message) {
        printStream.println(message.getMessage() + ANSI_RESET);
    }

    public void changePrintStream(PrintStream newPrintStream) {
        printStream = newPrintStream;
    }
}
