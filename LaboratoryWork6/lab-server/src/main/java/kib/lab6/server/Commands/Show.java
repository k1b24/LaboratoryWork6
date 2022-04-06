package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class Show extends AbstractCommand {

    public Show() {
        super("show", "Вывести все элементы коллекции", false);
    }

    @Override
    public Object execute(Request request) {
        //todo написать Мише, спросить насчет реализации сортировки объектов по размеру
        return new Response(new SuccessMessage("Элементы коллекции: "),
                Config.getCollectionManager().getSortedArrayListFromQueue());
    }
}
