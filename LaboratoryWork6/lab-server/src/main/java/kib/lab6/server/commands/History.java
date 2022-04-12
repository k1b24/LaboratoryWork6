package kib.lab6.server.commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class History extends AbstractCommand {

    public History() {
        super("history", "Вывести информацию по последним"
                + " 10 исполненным командам", false);
    }

    @Override
    public Object execute(Request request) {
        ArrayDeque<AbstractCommand> listToReturn = Config.getCommandManager().getLastExecutedCommands();
        return new Response(new SuccessMessage(listToReturn.stream()
                .map(AbstractCommand::getName)
                .collect(Collectors.joining("\n"))));
    }
}
