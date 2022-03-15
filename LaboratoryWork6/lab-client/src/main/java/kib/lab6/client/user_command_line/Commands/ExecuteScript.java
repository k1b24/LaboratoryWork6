package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.CommandListener;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ExecuteScript extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 1;
    private CommandListener commandListener;

    public ExecuteScript() {
        super("execute_script", "Выполнить скрипт из файла, принимает"
                + " на вход один аргумент [file_path]", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            String fileName = args[0];
            try {
                commandListener = new CommandListener(initializeFile(fileName));
            } catch (IOException e) {
                return new ErrorMessage(e.getMessage());
            }
            commandListener.readCommands();
            return new SuccessMessage("Скрипт завершил свою работу");
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }

    private InputStream initializeFile(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }
}
