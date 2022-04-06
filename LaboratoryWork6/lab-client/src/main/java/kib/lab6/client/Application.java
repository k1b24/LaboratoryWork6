package kib.lab6.client;

import kib.lab6.client.user_command_line.ClientCommandListener;
import kib.lab6.client.utils.ExecutableFileReader;
import kib.lab6.client.utils.RequestCreator;
import kib.lab6.common.InputedCommand;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.SuccessMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    private final Scanner scanner = new Scanner(System.in);
    private ConnectionHandlerClient connectionHandlerClient;
    private boolean listeningAndSendingModeOn = true;
    private final ClientCommandListener commandListener = new ClientCommandListener(System.in);
    private final RequestCreator requestCreator = new RequestCreator();

    public void launchApplication() {
        Config.getTextSender().printMessage(new SuccessMessage("Для начала работы с приложением вам потребуется ввести"
                + " адрес сервера, после подключения вы сможете работать с коллекцией в интерактивном режиме, для справки"
                + " по командам введите help"));
        inputInetAddress();
        if (connectionHandlerClient == null) {
            return;
        }
        while (listeningAndSendingModeOn) {
            InputedCommand userInputedCommand = commandListener.readCommand();
            if (userInputedCommand == null || "exit".equalsIgnoreCase(userInputedCommand.getName())) {
                listeningAndSendingModeOn = false;
            } else if ("execute_script".equalsIgnoreCase(userInputedCommand.getName())) {
                executeScript(userInputedCommand.getArguments());
            } else {
                if (sendRequest(userInputedCommand)) {
                    recieveResponse();
                }
            }
        }
    }

    private void inputInetAddress() {
        try {
            Config.getTextSender().printMessage(new SuccessMessage("Пожалуйста, введите адрес сервера в"
                    + " локальной сети с которым вы хотите работать"));
            String address = scanner.nextLine();
            connectionHandlerClient = new ConnectionHandlerClient(address);
            Config.getTextSender().printMessage(new SuccessMessage("Адрес в сети найден"));
        } catch (UnknownHostException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Такого адреса не существует в сети, повторите ввод"));
            inputInetAddress();
        } catch (SocketException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при открытии сетевого порта, пожалуйста, повторите ввод"));
        } catch (NoSuchElementException e) {
            connectionHandlerClient = null;
        }
    }

    private boolean sendRequest(InputedCommand inputedCommand) {
        Request request = requestCreator.createRequestFromInputedCommand(inputedCommand);
        if (request == null) {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода команды, введите help для "
                    + "получения справки по командам"));
            return false;
        } else {
            try {
                connectionHandlerClient.sendRequest(request);
                return true;
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при сериализации "
                        + "запроса, повторите попытку"));
                return false;
            }
        }
    }

    private void recieveResponse() {
        try {
            Response response = connectionHandlerClient.recieveResponse();
            Config.getTextSender().printMessage(response.getMessage());
            if (response.getPeople() != null) {
                Config.getTextSender().printMessage(
                        new SuccessMessage(response.getPeople().stream()
                        .map(HumanBeing::toString)
                        .collect(Collectors.joining("\n"))));
            }
        } catch (IOException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при получении ответа от сервера, попробуйте позже"));
        } catch (ClassNotFoundException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Сервер прислал пакет, который невозможно десериализовать"));
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
                        if (sendRequest(command)) {
                            recieveResponse();
                        }
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
