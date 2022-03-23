package kib.lab6.client.utils;

import kib.lab6.client.Config;
import kib.lab6.client.user_command_line.HumanInfoInput;
import kib.lab6.common.InputedCommand;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.entities.enums.Mood;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.StringToTypeConverter;

import java.util.Arrays;

public class RequestCreator {

    public Request createRequestFromInputedCommand(InputedCommand inputedCommand) {
        if (AvailableCommands.commandsWithoutArguments.contains(inputedCommand.getName().toLowerCase())) {
            //Обработка команды без аргументов
            return new Request(inputedCommand.getName());
        } else if (AvailableCommands.commandsWithNumberArgument.contains(inputedCommand.getName().toLowerCase())) {
            //Обработка команды с числовым аргументом
            try {
                return new Request(inputedCommand.getName(), (int) StringToTypeConverter.toObject(Integer.class, inputedCommand.getArguments()[0]));
            } catch (IllegalArgumentException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Введен неправильный числовой аргумент"));
                return null;
            }
        } else if (AvailableCommands.commandsWithMoodArgument.contains(inputedCommand.getName().toLowerCase())) {
            //Обработка команды с аргументом "настроение"
            Request request;
            if ("".equals(inputedCommand.getArguments()[0])) {
                request = new Request(inputedCommand.getName(), (Mood) null);
            } else {
                try {
                    request = new Request(inputedCommand.getName(), Mood.valueOf(inputedCommand.getArguments()[0]));
                } catch (IllegalArgumentException e) {
                    Config.getTextSender().printMessage(new ErrorMessage("Такого настроения не существует,"
                            + " введите одно из: " + Arrays.toString(Mood.values())));
                }
            }
        } else if (AvailableCommands.commandsWithHumanBeingArgument.contains(inputedCommand.getName().toLowerCase())) {
            //Обработка команды с аргументом "HumanBeing"
            HumanInfoInput humanInfoInput = new HumanInfoInput(inputedCommand.getArguments());
            humanInfoInput.inputHuman();
            HumanBeing humanForRequest = humanInfoInput.getNewHumanToInput();
            return new Request(inputedCommand.getName(), humanForRequest);
        } else if (AvailableCommands.commandsWithHumanBeingAndNumberArguments.contains(inputedCommand.getName().toLowerCase())) {
            //Обработка команды с аргументами "число" и "человек"
            try {
                int num = Integer.parseInt(inputedCommand.getArguments()[0]);
                String[] argumentsForHuman = Arrays.copyOfRange(inputedCommand.getArguments(), 1, inputedCommand.getArguments().length);
                HumanInfoInput humanInfoInput = new HumanInfoInput(argumentsForHuman);
                humanInfoInput.inputHuman();
                HumanBeing humanForRequest = humanInfoInput.getNewHumanToInput();
                return new Request(inputedCommand.getName(), num, humanForRequest);
            } catch (IllegalArgumentException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Введен неправильный числовой аргумент"));
                return null;
            }
        }
        return null;
    }

}
