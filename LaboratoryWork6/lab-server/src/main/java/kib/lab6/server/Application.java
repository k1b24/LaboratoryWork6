package kib.lab6.server;

import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.server.csv_parser.CSVReader;
import kib.lab6.server.utils.Config;
import kib.lab6.server.utils.ConnectionHandlerServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Application {

    private final CSVReader collectionFileReader;
    private ConnectionHandlerServer connectionHandlerServer;
    private final ConsoleListenerThread consoleListenerThread = new ConsoleListenerThread();

    public Application() {
        collectionFileReader = new CSVReader();
    }

    public void launchApplication() {
        boolean fillingResult = fillCollection();
        if (fillingResult) {
            try {
                connectionHandlerServer = new ConnectionHandlerServer();
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
                    connectionHandlerServer.sendResponse(responseToClient);
                }
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Не удалось получить пакет с клиента"));
            } catch (ClassNotFoundException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Клиент прислал пакет, который невозможно десериализовать"));
            }
        }
    }

    private boolean fillCollection() {
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
