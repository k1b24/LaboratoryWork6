package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.SuccessMessage;
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
