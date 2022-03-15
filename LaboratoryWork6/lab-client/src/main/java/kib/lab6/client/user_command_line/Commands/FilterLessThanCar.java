package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.entities.HumanBeing;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterLessThanCar extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 1;

    public FilterLessThanCar() {
        super("filter_less_than_car", "Вывести элементы, значение скорости которых"
                + " меньше заданного, принимает аргумент [Speed]", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            try {
                int speedFilter = Integer.parseInt(args[0]);
                ArrayList<HumanBeing> listToReturn = Config.getCollectionManager().filterByCarSpeed(speedFilter);
                return new SuccessMessage(listToReturn.stream()
                        .map(HumanBeing::toString)
                        .collect(Collectors.joining("\n")));
            } catch (NumberFormatException e) {
                return new ErrorMessage("Ошибка ввода скорости машины");
            }
        } else {
            return new ErrorMessage("Введено неправильное количество аргументов");
        }
    }
}
