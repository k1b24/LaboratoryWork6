package kib.lab6.client.user_command_line;

import kib.lab6.common.util.SmartSplitter;
import kib.lab6.common.InputedCommand;

import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс отвечающий за работу с пользователем в интерактивном режиме
 */
public class ClientCommandListener {

    private final Scanner scanner;

    /**
     * Конструктор
     */
    public ClientCommandListener(InputStream inputStream) {
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
            return  null;
        }
    }
}
