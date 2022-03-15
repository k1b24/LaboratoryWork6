package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.util.List;
import java.util.stream.Collectors;

public class Help extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public Help() {
        super("help", "Вывести справку по доступным командам", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            List<AbstractCommand> listToReturn = getCommandsList();
            return new SuccessMessage(listToReturn.stream()
                    .map(AbstractCommand::getDescription)
                    .collect(Collectors.joining("\n")));
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
