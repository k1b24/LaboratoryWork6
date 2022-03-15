package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

public class Clear extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public Clear() {
        super("clear", "Очистить коллекцию", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            Config.getCollectionManager().clearCollection();
            return new SuccessMessage("Коллекция успешно очищена");
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }
}
