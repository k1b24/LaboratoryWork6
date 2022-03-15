package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.entities.enums.Mood;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.util.Arrays;

public class RemoveByAnyMood extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;

    public RemoveByAnyMood() {
        super("remove_by_any_mood", "Удалить любого человека из коллекции по его настроению,"
                + " принимает на вход тип настроения " + Arrays.toString(Mood.values()), AMOUNT_OF_ARGS);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            Mood moodToRemove;
            if ("".equals(args[0])) {
                moodToRemove = null;
            } else {
                try {
                    moodToRemove = Mood.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return new ErrorMessage("Такого настроения не существует, введите одно из: \n" + Arrays.toString(Mood.values()));
                }
            }
            Config.getCollectionManager().removeHumanByAnyMood(moodToRemove);
            return new SuccessMessage("Случайный человек с настроением " + args[0] + " удален");
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
