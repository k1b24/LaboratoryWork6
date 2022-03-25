package kib.lab6.server;

import kib.lab6.common.abstractions.AbstractMessage;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.server.csv_parser.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    private final CSVReader collectionFileReader;
    private ConnectionHandlerServer connectionHandlerServer;

    public Application() {
        collectionFileReader = new CSVReader();
    }

    public void launchApplication() {
        boolean fillingResult = fillCollection();
        if (fillingResult) {
            try {
                connectionHandlerServer = new ConnectionHandlerServer();
                launchMainLoop();
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Не удалось открыть канал для прослушивания"));
            }
        }
    }

    private void launchMainLoop() {
        while (true) { //TODO еблан не испльзуй вайл тру
            try {
                Request requestFromClient = connectionHandlerServer.listen();
                Response responseToClient = new Response((AbstractMessage) Config.getCommandManager().execute(requestFromClient));
                connectionHandlerServer.sendResponse(responseToClient);
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Не удалось получить пакет с клиента"));
                e.printStackTrace();
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
