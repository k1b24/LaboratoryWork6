package kib.lab6.server.Commands;

import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;


public class AddIfMin extends AbstractCommand {

    public AddIfMin() {
        super("add_if_min", "Добавить введенный элемент в коллекцию, если его значение минимально,"
                + " принимает на вход [Имя, наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", false);
    }

    @Override
    public Object execute(Request request) {
        boolean isAdded = Config.getCollectionManager().addIfMin(request.getHumanToSend());
        if (isAdded) {
            return new SuccessMessage("Объект успешно добавлен в коллекцию");
        } else {
            return new ErrorMessage("Объект не был добавлен в коллекцию, так как он не является минимальным");
        }

    }
}
