package kib.lab6.common.util.console_workers;

import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс отвечающий за работу с пользователем в интерактивном режиме
 */
public class CommandListener {

    private final Scanner scanner;

    /**
     * Конструктор
     */
    public CommandListener(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public InputedCommand readCommand() {
        try {
            System.out.print("↪ ");
            String line = scanner.nextLine();
            String[] inputString = SmartSplitter.smartSplit(line).toArray(new String[0]);
            String commandName = inputString[0].toLowerCase();
            String[] commandArgs = Arrays.copyOfRange(inputString, 1, inputString.length);
            return new InputedCommand(commandName, commandArgs);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
