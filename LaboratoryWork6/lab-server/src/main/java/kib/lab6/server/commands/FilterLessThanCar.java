package kib.lab6.server.commands;

import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.console_workers.SuccessMessage;

public class FilterLessThanCar extends AbstractCommand {

    public FilterLessThanCar() {
        super("filter_less_than_car", "Вывести элементы, значение скорости которых"
                + " меньше заданного, принимает аргумент [Speed]", false);
    }

    @Override
    public Object execute(Request request) {
        int speedFilter = request.getNumberArgumentToSend();
        return new Response(new SuccessMessage("Элементы коллекции, значение скорости которых меньше " + speedFilter + ":"),
                Config.getCollectionManager().filterByCarSpeed(speedFilter));
    }
}
