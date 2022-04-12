package kib.lab6.client;

import kib.lab6.common.util.console_workers.CommandListener;
import kib.lab6.common.util.ExecutableFileReader;
import kib.lab6.common.util.client_server_communication.RequestCreator;
import kib.lab6.common.util.console_workers.InputedCommand;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.common.util.console_workers.TextSender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

    private static final int MAX_PORT_VALUE = 65535;
    private final Scanner scanner = new Scanner(System.in);
    private ConnectionHandlerClient connectionHandlerClient;
    private boolean listeningAndSendingModeOn = true;
    private final CommandListener commandListener = new CommandListener(System.in);
    private final TextSender textSender = new TextSender(System.out);

    public void launchApplication() {
        textSender.printMessage(new SuccessMessage("Для начала работы с приложением вам потребуется ввести"
                + " адрес сервера, после подключения вы сможете работать с коллекцией в интерактивном режиме, для справки"
                + " по командам введите help"));
        inputInetAddress();
        inputPort();
        if (!(connectionHandlerClient == null)) {
            launchMainLoop();
        }
    }

    private void launchMainLoop() {
        while (listeningAndSendingModeOn) {
            InputedCommand userInputedCommand = commandListener.readCommand();
            if (userInputedCommand == null
                    || ("exit".equalsIgnoreCase(userInputedCommand.getName())
                    && userInputedCommand.getArguments().length == 0)) {
                listeningAndSendingModeOn = false;
            } else if ("execute_script".equalsIgnoreCase(userInputedCommand.getName())
                    && userInputedCommand.getArguments().length == 1) {
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
            textSender.printMessage(new SuccessMessage("Пожалуйста, введите адрес сервера в"
                    + " локальной сети с которым вы хотите работать"));
            String address = scanner.nextLine();
            connectionHandlerClient = new ConnectionHandlerClient(address);
            textSender.printMessage(new SuccessMessage("Адрес в сети найден"));
        } catch (UnknownHostException e) {
            textSender.printMessage(new ErrorMessage("Такого адреса не существует в сети, повторите ввод"));
            inputInetAddress();
        } catch (SocketException e) {
            textSender.printMessage(new ErrorMessage("Произошла ошибка при открытии сетевого порта, пожалуйста, повторите ввод"));
            inputInetAddress();
        } catch (NoSuchElementException e) {
            connectionHandlerClient = null;
        }
    }

    private void inputPort() {
        try {
            textSender.printMessage(new SuccessMessage("Пожалуйста, введите порт сервера в"
                    + " с которым вы хотите работать"));
            String inputedPort = scanner.nextLine();
            int port = Integer.parseInt(inputedPort);
            if (port >= 1 && port <= MAX_PORT_VALUE) {
                connectionHandlerClient.setServerPort(port);
            } else {
                textSender.printMessage(new ErrorMessage("Вы ввели неверный порт, повторите ввод"));
                inputPort();
            }
        } catch (NoSuchElementException e) {
            connectionHandlerClient = null;
        } catch (NumberFormatException e) {
            textSender.printMessage(new ErrorMessage("Вы ввели неверный порт, повторите ввод"));
            inputPort();
        }
    }

    private boolean sendRequest(InputedCommand inputedCommand) {
        RequestCreator requestCreator = new RequestCreator(textSender);
        Request request = requestCreator.createRequestFromInputedCommand(inputedCommand);
        if (request == null) {
            textSender.printMessage(new ErrorMessage("Ошибка ввода команды, введите help для "
                    + "получения справки по командам"));
            return false;
        } else {
            try {
                connectionHandlerClient.sendRequest(request);
                return true;
            } catch (IOException e) {
                textSender.printMessage(new ErrorMessage("Произошла ошибка при сериализации "
                        + "запроса, повторите попытку"));
                return false;
            }
        }
    }

    private void recieveResponse() {
        try {
            Response response = connectionHandlerClient.recieveResponse();
            textSender.printMessage(response.getMessage());
            if (response.getPeople() != null) {
                textSender.printMessage(
                        new SuccessMessage(response.getPeople().stream()
                                .map(HumanBeing::toString)
                                .collect(Collectors.joining("\n"))));
            }
        } catch (IOException e) {
            textSender.printMessage(new ErrorMessage("Произошла ошибка при получении ответа от сервера, попробуйте позже"));
        } catch (ClassNotFoundException e) {
            textSender.printMessage(new ErrorMessage("Сервер прислал пакет, который невозможно десериализовать"));
        }
    }

    private void executeScript(String[] arguments) {
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
                    textSender.printMessage(new ErrorMessage("Команда execute_script пропущена"));
                }
            }
        } catch (FileNotFoundException e) {
            textSender.printMessage(new ErrorMessage("Файл " + arguments[0] + " не найден"));
        }
    }
}
