package kib.lab6.client.utils;

import kib.lab6.client.abstractions.AbstractMessage;

import java.io.PrintStream;

public final class TextSender {

    private static final String MESSAGE_COLOR = "\u001B[32m"; //ANSI_GREEN
    private static final String ERROR_COLOR = "\u001B[31m"; //ANSI_RED
    private static final String ANSI_RESET = "\u001B[0m";
    private static PrintStream printStream = System.out;

    private TextSender() {

    }

    public static void printText(String message) {
        printStream.println(MESSAGE_COLOR + message + ANSI_RESET);
    }

    public static void printError(String message) {
        printStream.println(ERROR_COLOR + message + ANSI_RESET);
    }

    public static void printMessage(AbstractMessage message) {
        printStream.println(message.getMessage());
    }

    public static void changePrintStream(PrintStream newPrintStream) {
        printStream = newPrintStream;
    }
}
