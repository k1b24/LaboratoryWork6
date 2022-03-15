package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class History extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public History() {
        super("history", "Вывести информацию по последним"
                + " 10 исполненным командам", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            ArrayDeque<AbstractCommand> listToReturn = Config.getCommandManager().getLastExecutedCommands();
            return new SuccessMessage(listToReturn.stream()
                    .map(AbstractCommand::getName)
                    .collect(Collectors.joining("\n")));
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
