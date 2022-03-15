package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.HumanInfoInput;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.util.Arrays;

public class Update extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 5;

    public Update() {
        super("update", "Обновить элемент коллекции по его ID, принимает на вход [ID, Имя,"
                + " наличие героизма(true/false), наличие зубочистки(true/false), скорость удара]", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            try {
                int id = Integer.parseInt(args[0]);
                HumanInfoInput humanInfoInput = new HumanInfoInput(Config.getCollectionManager().getHumanById(id), Arrays.copyOfRange(args, 1, args.length));
                humanInfoInput.inputHuman();
                Config.getCollectionManager().setHumanById(id, humanInfoInput.getNewHumanToInput());
                return new SuccessMessage("Объект успешно добавлен в коллекцию");
            } catch (IllegalArgumentException e) {
                return new ErrorMessage(e.getMessage());
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }

}
