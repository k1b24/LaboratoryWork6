package kib.lab6.server.Commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class Show extends AbstractCommand {

    public Show() {
        super("show", "Вывести все элементы коллекции", false);
    }

    @Override
    public Object execute(Request request) {
        //todo Сортировка объектов по размеру
        return new Response(new SuccessMessage("Элементы коллекции: "),
                Config.getCollectionManager().getSortedArrayListFromQueue());
    }
}
