package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.entities.HumanBeing;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.util.List;
import java.util.stream.Collectors;

public class PrintDescending extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public PrintDescending() {
        super("print_descending", "Вывести"
                + " коллекциию в порядке убывания", AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            List<HumanBeing> listToReturn = Config.getCollectionManager().returnDescending();
            return new SuccessMessage(listToReturn.stream()
                    .map(HumanBeing::toString)
                    .collect(Collectors.joining("\n")));
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
