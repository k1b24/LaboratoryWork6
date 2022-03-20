package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.server.Config;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.SuccessMessage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterLessThanCar extends AbstractCommand {

    public FilterLessThanCar() {
        super("filter_less_than_car", "Вывести элементы, значение скорости которых"
                + " меньше заданного, принимает аргумент [Speed]");
    }

    @Override
    public Object execute(Request request) {
        int speedFilter = Integer.parseInt(request.getCommandArgumentToSend());
        ArrayList<HumanBeing> listToReturn = Config.getCollectionManager().filterByCarSpeed(speedFilter);
        return new SuccessMessage(listToReturn.stream()
                .map(HumanBeing::toString)
                .collect(Collectors.joining("\n")));
    }
}