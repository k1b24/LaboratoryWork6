package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.SuccessMessage;

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
