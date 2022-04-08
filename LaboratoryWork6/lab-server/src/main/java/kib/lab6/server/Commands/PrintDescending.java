package kib.lab6.server.Commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class PrintDescending extends AbstractCommand {

    public PrintDescending() {
        super("print_descending", "Вывести"
                + " коллекциию в порядке убывания", false);
    }

    @Override
    public Object execute(Request request) {
        return new Response(new SuccessMessage("Коллекция в порядке убывания: "),
                Config.getCollectionManager().returnDescending());
    }
}
