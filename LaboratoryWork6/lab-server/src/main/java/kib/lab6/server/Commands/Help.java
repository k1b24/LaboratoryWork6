package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.SuccessMessage;

import java.util.List;
import java.util.stream.Collectors;

public class Help extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public Help() {
        super("help", "Вывести справку по доступным командам", false);
    }

    @Override
    public Object execute(Request request) {
        List<AbstractCommand> listToReturn = getCommandsList();
        return new SuccessMessage(listToReturn.stream()
                .map(AbstractCommand::getDescription)
                .collect(Collectors.joining("\n")));
    }
}
