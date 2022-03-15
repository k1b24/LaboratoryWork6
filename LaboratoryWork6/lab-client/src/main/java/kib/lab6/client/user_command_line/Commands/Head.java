package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.entities.HumanBeing;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

public class Head extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public Head() {
        super("head", "Вывести первый элемент коллекции(голову очереди)", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            HumanBeing head = Config.getCollectionManager().returnHead();
            if (head != null) {
                return new SuccessMessage(head.toString());
            } else {
                return new ErrorMessage("Коллекция пустая :(");
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
