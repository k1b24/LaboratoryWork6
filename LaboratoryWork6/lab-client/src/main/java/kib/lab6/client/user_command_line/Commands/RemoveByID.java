package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

public class RemoveByID extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public RemoveByID() {
        super("remove_by_id", "Удалить человека из коллекции по"
                + " его ID, принимает на вход [ID]", AMOUNT_OF_ARGS);
    }
    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            try {
                int id = Integer.parseInt(args[0]);
                if (id <= Config.getCollectionManager().getLength() && id > 0) {
                    Config.getCollectionManager().removeHumanById(id);
                    return new SuccessMessage("Человек с ID " + id + " успешно удален");
                } else {
                    return new ErrorMessage("Человек с таким ID не найден");
                }
            } catch (NumberFormatException e) {
                return new ErrorMessage("Передано неправильное значение ID");
            }
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }
}
