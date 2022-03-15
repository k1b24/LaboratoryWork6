package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.HumanInfoInput;
import kib.lab6.client.user_command_line.SuccessMessage;

public class AddIfMin extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 4;

    public AddIfMin() {
        super("add_if_min", "Добавить введенный элемент в коллекцию, если его значение минимально,"
                + " принимает на вход [Имя, наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            try {
                HumanInfoInput humanInfoInput = new HumanInfoInput(args);
                humanInfoInput.inputHuman();
                Config.getCollectionManager().addIfMin(humanInfoInput.getNewHumanToInput());
                return new SuccessMessage("Объект успешно добавлен в коллекцию");
            } catch (IllegalArgumentException e) {
                return new ErrorMessage(e.getMessage());
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
