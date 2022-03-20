package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.Config;
import kib.lab6.server.abstractions.AbstractCommand;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class History extends AbstractCommand {

    public History() {
        super("history", "Вывести информацию по последним"
                + " 10 исполненным командам");
    }

    @Override
    public Object execute(Request request) {
        ArrayDeque<AbstractCommand> listToReturn = Config.getCommandManager().getLastExecutedCommands();
        return new SuccessMessage(listToReturn.stream()
                .map(AbstractCommand::getName)
                .collect(Collectors.joining("\n")));
    }
}
