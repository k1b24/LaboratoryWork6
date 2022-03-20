package kib.lab6.server.Commands;


import kib.lab6.common.entities.enums.Mood;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.Config;
import kib.lab6.server.abstractions.AbstractCommand;

import java.util.Arrays;

public class RemoveByAnyMood extends AbstractCommand {

    public RemoveByAnyMood() {
        super("remove_by_any_mood", "Удалить любого человека из коллекции по его настроению,"
                + " принимает на вход тип настроения " + Arrays.toString(Mood.values()));
    }

    @Override
    public Object execute(Request request) {
        Mood moodToRemove;
        if ("".equals(request.getCommandArgumentToSend())) {
            moodToRemove = null;
        } else {
            moodToRemove = Mood.valueOf(request.getCommandArgumentToSend().toUpperCase());
        }
        Config.getCollectionManager().removeHumanByAnyMood(moodToRemove);
        return new SuccessMessage("Случайный человек с настроением " + request.getCommandArgumentToSend() + " удален");
    }
}
