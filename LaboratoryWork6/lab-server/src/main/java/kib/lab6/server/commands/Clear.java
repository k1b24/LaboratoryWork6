package kib.lab6.server.commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class Clear extends AbstractCommand {

    public Clear() {
        super("clear", "Очистить коллекцию", false);
    }

    @Override
    public Object execute(Request request) {
        Config.getCollectionManager().clearCollection();
        return new Response(new SuccessMessage("Коллекция успешно очищена"));
    }
}
