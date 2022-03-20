package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class Info extends AbstractCommand {

    public Info() {
        super("info", "Вывести информацию о коллекции");
    }

    @Override
    public Object execute(Request request) {
        return new SuccessMessage(Config.getCollectionManager().getInfoAboutCollection());
    }
}
