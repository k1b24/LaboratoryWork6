package kib.lab6.server.commands;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.server.utils.ObjectSizeAnalyzer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Show extends AbstractCommand {

    public Show() {
        super("show", "Вывести все элементы коллекции", false);
    }

    @Override
    public Object execute(Request request) {
        ArrayList<HumanBeing> people = Config.getCollectionManager().getSortedArrayListFromQueue();
        return new Response(new SuccessMessage("Элементы коллекции: "),
                (ArrayList<HumanBeing>) people.stream().sorted(Comparator.comparing(ObjectSizeAnalyzer::getSize).reversed()).collect(Collectors.toList()));
    }
}
