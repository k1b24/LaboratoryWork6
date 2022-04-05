package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class Show extends AbstractCommand {

    public Show() {
        super("show", "Вывести все элементы коллекции", false);
    }

    @Override
    public Object execute(Request request) {
        return new SuccessMessage(Config.getCollectionManager().getStringForShowing());
    }
}
