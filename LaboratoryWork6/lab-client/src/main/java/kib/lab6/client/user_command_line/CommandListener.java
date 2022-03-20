package kib.lab6.client.user_command_line;

import kib.lab6.client.Config;
import kib.lab6.client.utils.SmartSplitter;
import kib.lab6.common.abstractions.AbstractMessage;
import kib.lab6.common.util.TextSender;

import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс отвечающий за работу с пользователем в интерактивном режиме
 */
public class CommandListener {

    private boolean isRunning;
    private final InputStream commandsInputStream;

    /**
     * Конструктор
     */
    public CommandListener(InputStream inputStream) {
        TextSender.printText("Добро пожаловать в интерактивный режим работы с коллекцией, "
               + "введите help, чтобы узнать информацию о доступных командах");
        commandsInputStream = inputStream;
    }

    /**
     * Метод, читающий команды из System.in до тех пор, пока не возникнет команда exit
     */
    public void readCommands() {
        isRunning = true;
        Scanner scanner = new Scanner(commandsInputStream);
        while (isRunning) {
            try {
                String line = scanner.nextLine();
                if ("exit".equals(line)) {
                    isRunning = false;
                    continue;
                }
                String[] inputString = SmartSplitter.smartSplit(line).toArray(new String[0]);
                String commandName = inputString[0].toLowerCase();
                String[] commandArgs = Arrays.copyOfRange(inputString, 1, inputString.length);
                TextSender.printMessage((AbstractMessage) Config.getCommandManager().execute(commandName.toLowerCase(), commandArgs));
            } catch (NoSuchElementException e) {
                break;
            }
        }
        scanner.close();
    }

}
