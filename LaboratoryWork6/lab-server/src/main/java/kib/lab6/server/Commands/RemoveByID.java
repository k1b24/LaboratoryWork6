package kib.lab6.server.Commands;

import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class RemoveByID extends AbstractCommand {

    public RemoveByID() {
        super("remove_by_id", "Удалить человека из коллекции по"
                + " его ID, принимает на вход [ID]", false);
    }

    @Override
    public Object execute(Request request) {
        int id = request.getNumberArgumentToSend();
        if (Config.getCollectionManager().getIDs().contains(id) && id > 0) {
            Config.getCollectionManager().removeHumanById(id);
            return new Response(new SuccessMessage("Человек с ID " + id + " успешно удален"));
        } else {
            return new Response(new ErrorMessage("Человек с таким ID не найден"));
        }
    }
}
