package kib.lab6.server.Commands;

import kib.lab6.server.Config;
import kib.lab6.common.util.Request;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.SuccessMessage;

public class Add extends AbstractCommand {

    public Add() {
        super("add", "Добавить элемент в коллекцию, принимает на вход [Имя, наличие"
                + " героизма(true/false), наличие зубочистки(true/false), скорость удара]");
    }

    @Override
    public Object execute(Request request) {
        Config.getCollectionManager().addHuman(request.getHumanToSend());
        return new SuccessMessage("Объект успешно добавлен в коллекцию");
    }
}
