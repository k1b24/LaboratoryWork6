package kib.lab6.client;

import kib.lab6.client.user_command_line.ClientCommandListener;
import kib.lab6.client.utils.RequestCreator;
import kib.lab6.common.InputedCommand;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.SuccessMessage;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Application {

    private final Scanner scanner = new Scanner(System.in);
    private ConnectionHandlerClient connectionHandlerClient;
    private boolean listeningAndSendingModeOn = true;
    private ClientCommandListener commandListener = new ClientCommandListener(System.in);
    private final RequestCreator requestCreator = new RequestCreator();

    public void launchApplication() {
        Config.getTextSender().printMessage(new SuccessMessage("Для начала работы с приложением вам потребуется ввести"
                + " адрес сервера, после подключения вы сможете работать с коллекцией в интерактивном режиме, для справки"
                + " по командам введите help"));
        inputInetAddress();
        while (listeningAndSendingModeOn) {
            // Процесс отправки команды
            InputedCommand userInputedCommand = commandListener.readCommand();
            if ("exit".equals(userInputedCommand.getName().toLowerCase())) {
                listeningAndSendingModeOn = false;
                continue;
            } else {
                Request request = requestCreator.createRequestFromInputedCommand(userInputedCommand);

                if (request == null) {
                    Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода команды, введите help для "
                            + "получения справки по командам"));
                    continue;
                }

                try {
                    connectionHandlerClient.sendRequest(request);
                } catch (IOException e) {
                    Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при сериализации "
                            + "запроса, повторите попытку"));
                }

                try {
                    Response response = connectionHandlerClient.recieveResponse();
                    Config.getTextSender().printMessage(response.getMessage());
                } catch (IOException e) {
                    Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при получении ответа от сервера, попробуйте позже"));
                } catch (ClassNotFoundException e) {
                    Config.getTextSender().printMessage(new ErrorMessage("Сервер прислал пакет, который невозможно десериализовать"));
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
        } catch (UnknownHostException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Такого адреса не существует в сети, повторите ввод"));
            inputInetAddress();
        } catch (SocketException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при открытие сетевого порта, пожалуйста, повторите ввод"));
        }
    }
}
