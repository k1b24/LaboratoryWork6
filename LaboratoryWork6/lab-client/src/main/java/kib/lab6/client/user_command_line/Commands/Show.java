package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

public class Show extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public Show() {
        super("show", "Вывести все элементы коллекции", AMOUNT_OF_ARGS);
    }
    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            return new SuccessMessage(Config.getCollectionManager().getStringForShowing());
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
