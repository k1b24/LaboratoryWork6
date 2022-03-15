package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

public class Info extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public Info() {
        super("info", "Вывести информацию о коллекции", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            return new SuccessMessage(Config.getCollectionManager().getInfoAboutCollection());
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
