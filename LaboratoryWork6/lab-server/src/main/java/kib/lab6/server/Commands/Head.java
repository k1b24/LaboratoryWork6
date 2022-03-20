package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.server.Config;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.SuccessMessage;

public class Head extends AbstractCommand {

    public Head() {
        super("head", "Вывести первый элемент коллекции(голову очереди)");
    }

    @Override
    public Object execute(Request request) {
        HumanBeing head = Config.getCollectionManager().returnHead();
        if (head != null) {
            return new SuccessMessage(head.toString());
        } else {
            return new ErrorMessage("Коллекция пустая :(");
        }
    }
}
