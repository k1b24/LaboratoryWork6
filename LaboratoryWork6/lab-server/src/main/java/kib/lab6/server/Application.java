package kib.lab6.server;

import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.csv_parser.CSVReader;
import kib.lab6.server.utils.Config;
import kib.lab6.server.utils.ConnectionHandlerServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {

    private static final int MAX_PORT_VALUE = 65535;
    private ConnectionHandlerServer connectionHandlerServer;
    private final ConsoleListenerThread consoleListenerThread = new ConsoleListenerThread();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Публичный метод, запускающий работу серверного приложения
     * Заполняет коллекцию и вводит с консоли порт, на котором будет слушать данные с клиента
     */
    public void launchApplication() {
        boolean fillingResult = fillCollection();
        if (fillingResult) {
            try {
                Integer port = inputPort();
                if (port != null) {
                    connectionHandlerServer = new ConnectionHandlerServer(port);
                    Config.getTextSender().printMessage(new SuccessMessage("Сервер запущен"));
                } else {
                    return;
                }
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Не удалось открыть канал для прослушивания"));
                return;
            }
            consoleListenerThread.start();
            launchMainLoop();
        }
    }

    private void launchMainLoop() {
        Scanner s = new Scanner(System.in);
        while (Config.isWorking()) {
            try {
                Request requestFromClient = connectionHandlerServer.listen();
                if (requestFromClient != null) {
                    Response responseToClient = (Response) Config.getCommandManager().executeCommandFromRequest(requestFromClient);
                    try {
                        connectionHandlerServer.sendResponse(responseToClient);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Config.getTextSender().printMessage(new ErrorMessage("Не удалось отправить ответ клиенту"));
                    }
                }
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Не удалось получить пакет с клиента"));
            } catch (ClassNotFoundException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Клиент прислал пакет, который невозможно десериализовать"));
            }
        }
        try {
            connectionHandlerServer.closeServer();
        } catch (IOException e) {
            Config.getTextSender().printMessage(new ErrorMessage("При закрытии сервера произошла ошибка, "
                    + "сервер закончил работу некорректно"));
        }
    }

    private Integer inputPort() {
        try {
            Config.getTextSender().printMessage(new SuccessMessage("Пожалуйста, введите порт сервера в"
                    + " с которым вы хотите работать"));
            String inputedPort = scanner.nextLine();
            int port = Integer.parseInt(inputedPort);
            if (port >= 1 && port <= MAX_PORT_VALUE) {
                return port;
            } else {
                Config.getTextSender().printMessage(new ErrorMessage("Вы ввели неверный порт, повторите ввод"));
                return inputPort();
            }
        } catch (NoSuchElementException e) {
            return null;
        } catch (NumberFormatException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Вы ввели неверный порт, повторите ввод"));
            return inputPort();
        }
    }

    private boolean fillCollection() {
        CSVReader collectionFileReader = new CSVReader();
        try {
            collectionFileReader.initializeFile(Config.getFilePath());
            collectionFileReader.parseFile();
            Config.getCollectionManager().fillWithArray(collectionFileReader.getInfoFromFile());
        } catch (FileNotFoundException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Файл: " + Config.getFilePath() + " не найден"));
            return false;
        } catch (NullPointerException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Пожалуйста проинциализируйте системную переменную HUMAN_INFO, "
                    + "содержащую путь до файла с информацией о коллекции"));
            return false;
        }
        return true;
    }
}
