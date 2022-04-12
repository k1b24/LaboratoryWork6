package kib.lab6.server;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.ExecutableFileReader;
import kib.lab6.common.util.console_workers.CommandListener;
import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.console_workers.InputedCommand;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.RequestCreator;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.utils.Config;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ConsoleListenerThread extends Thread {

    @Override
    public void run() {
        CommandListener commandListener = new CommandListener(System.in);
        while (Config.isWorking()) {
            InputedCommand userInputedCommand = commandListener.readCommand();
            if (userInputedCommand == null
                    || ("exit".equalsIgnoreCase(userInputedCommand.getName())
                        && userInputedCommand.getArguments().length == 0)) {
                Config.setIsWorking(false);
            } else if ("execute_script".equalsIgnoreCase(userInputedCommand.getName())
                    && userInputedCommand.getArguments().length == 1) {
                executeScript(userInputedCommand.getArguments());
            } else {
                executeRequest(userInputedCommand);
            }
        }
    }

    private void executeRequest(InputedCommand inputedCommand) {
        RequestCreator requestCreator = new RequestCreator(Config.getTextSender());
        Request request = requestCreator.createRequestFromInputedCommand(inputedCommand);
        if (request != null) {
            request.setServerRequest(true);
            Response response = (Response) Config.getCommandManager().executeCommandFromRequest(request);
            Config.getTextSender().printMessage(response.getMessage());
            if (response.getPeople() != null) {
                Config.getTextSender().printMessage(new SuccessMessage(response.getPeople().stream()
                        .map(HumanBeing::toString)
                        .collect(Collectors.joining("\n"))));
            }
        } else {
            Config.getTextSender().printMessage(new ErrorMessage("Такой команды не существует"));
        }
    }

    private void executeScript(String[] arguments) {
        if (arguments.length == 1) {
            try {
                ExecutableFileReader fileReader = new ExecutableFileReader();
                fileReader.initializeFile(arguments[0]);
                fileReader.parseFile();
                ArrayList<InputedCommand> commandsFromFile = fileReader.getInfoFromFile();
                for (InputedCommand command : commandsFromFile) {
                    if (!"execute_script".equalsIgnoreCase(command.getName())) {
                        executeRequest(command);
                    } else {
                        Config.getTextSender().printMessage(new ErrorMessage("Команда execute_script пропущена"));
                    }
                }
            } catch (FileNotFoundException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Файл " + arguments[0] + " не найден"));
            }
        } else {
            Config.getTextSender().printMessage(new ErrorMessage(""));
        }
    }
}
