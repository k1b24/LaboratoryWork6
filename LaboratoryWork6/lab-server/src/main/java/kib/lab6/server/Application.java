package kib.lab6.server;

import kib.lab6.common.abstractions.AbstractMessage;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.server.csv_parser.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Application {

    private final CSVReader collectionFileReader;

    public Application() {
        collectionFileReader = new CSVReader();
    }

    public void launchApplication() {
        boolean fillingResult = fillCollection();
        if (fillingResult) {
            try {
                ConnectionHandlerServer connectionHandlerServer = new ConnectionHandlerServer();
                while (true) { //Сделать не вайл тру, подумать как поменять
                    //TODO Command listener, который тоже слушает команды, но со стороны сервера и так же формирует запросы для комманд манагера
                    Request requestFromClient = connectionHandlerServer.listen();
                    AbstractMessage message = (AbstractMessage) Config.getCommandManager().execute(requestFromClient);
                    //TODO Упаковка сообщения и отправка его обратно, все разбить по методам!!!!
                }
//                connectionHandlerServer.closeConnection();
            } catch (IOException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при попытке открытия порта"));
            } catch (ClassNotFoundException e) {
                Config.getTextSender().printMessage(new ErrorMessage("От клиента были получены неизвестные данные"));
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
