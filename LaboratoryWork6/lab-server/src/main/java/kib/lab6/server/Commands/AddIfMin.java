package kib.lab6.server.Commands;

import kib.lab6.common.abstractions.AbstractMessage;
import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
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
        AbstractMessage message;
        if (isAdded) {
            message = new SuccessMessage("Объект успешно добавлен в коллекцию");
        } else {
            message = new ErrorMessage("Объект не был добавлен в коллекцию, так как он не является минимальным");
        }
        return new Response(message);
    }
}
