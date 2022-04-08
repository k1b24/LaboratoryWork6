package kib.lab6.server.Commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.console_workers.SuccessMessage;

import java.util.ArrayList;

public class Head extends AbstractCommand {

    public Head() {
        super("head", "Вывести первый элемент коллекции(голову очереди)", false);
    }

    @Override
    public Object execute(Request request) {
        HumanBeing head = Config.getCollectionManager().returnHead();
        ArrayList<HumanBeing> listToReturn = new ArrayList<>();
        listToReturn.add(head);
        if (head != null) {
            return new Response(new SuccessMessage("Первый элемент коллекции: "), listToReturn);
        } else {
            return new Response(new ErrorMessage("Коллекция пустая :("));
        }
    }
}
