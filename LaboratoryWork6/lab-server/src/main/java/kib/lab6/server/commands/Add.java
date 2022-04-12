package kib.lab6.server.commands;

import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.server.utils.Config;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.console_workers.SuccessMessage;

public class Add extends AbstractCommand {

    public Add() {
        super("add", "Добавить элемент в коллекцию, принимает на вход [Имя, наличие"
                + " героизма(true/false), наличие зубочистки(true/false), скорость удара]", false);
    }

    @Override
    public Object execute(Request request) {
        Config.getCollectionManager().addHuman(request.getHumanToSend());
        return new Response(new SuccessMessage("Объект успешно добавлен в коллекцию"));
    }
}
