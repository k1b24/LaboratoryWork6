package kib.lab6.server.commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class Update extends AbstractCommand {

    public Update() {
        super("update", "Обновить элемент коллекции по его ID, принимает на вход [ID, Имя,"
                + " наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", false);
    }

    @Override
    public Object execute(Request request) {
        int id = request.getNumberArgumentToSend();
        Config.getCollectionManager().setHumanById(id, request.getHumanToSend());
        return new Response(new SuccessMessage("Объект успешно добавлен в коллекцию"));
    }
}
