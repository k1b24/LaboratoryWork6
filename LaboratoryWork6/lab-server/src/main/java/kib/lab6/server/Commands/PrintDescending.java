package kib.lab6.server.Commands;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;

import java.util.List;
import java.util.stream.Collectors;

public class PrintDescending extends AbstractCommand {

    public PrintDescending() {
        super("print_descending", "Вывести"
                + " коллекциию в порядке убывания", false);
    }

    @Override
    public Object execute(Request request) {
        List<HumanBeing> listToReturn = Config.getCollectionManager().returnDescending();
        return new SuccessMessage(listToReturn.stream()
                .map(HumanBeing::toString)
                .collect(Collectors.joining("\n")));
    }
}
