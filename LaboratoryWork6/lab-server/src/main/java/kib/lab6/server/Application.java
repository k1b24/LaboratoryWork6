package kib.lab6.server;

import kib.lab6.common.abstractions.AbstractMessage;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.TextSender;
import kib.lab6.server.csv_parser.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    private final CSVReader collectionFileReader;

    public Application() {
        collectionFileReader = new CSVReader();
    }

    public void launchApplication() {
        boolean fillingResult = fillCollection();
        System.in;
        if (fillingResult) {
            RequestHandler requestHandler = new RequestHandler();
            while (true) {
                try (ConnectionHandlerServer connectionHandlerServer = new ConnectionHandlerServer()) {
                    //Сделать не вайл тру, подумать как поменять
                    //TODO Command listener, который тоже слушает команды, но со стороны сервера и
                    // так же формирует запросы для комманд манагера, это надо делать параллельно (хуй пойми как)
                    // Не забывать про то, что сервер может одновременно получить команду от
                    // клиента и от самого себя с консоли, ответ где-то в java nio и селекторах
                    Request requestFromClient = requestHandler.createRequestFromBuffer(connectionHandlerServer.listen());
                    Config.getTextSender().printMessage((AbstractMessage) Config.getCommandManager().execute(requestFromClient));
//                    connectionHandlerServer.sendResponse(new Response();
                    //TODO Упаковка сообщения и отправка его обратно, все разбить по методам!!!!
                } catch (IOException e) {
//                Config.getTextSender().printMessage(new ErrorMessage("Произошла ошибка при попытке открытия порта"));
//                e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    Config.getTextSender().printMessage(new ErrorMessage("От клиента были получены неизвестные данные"));
                }
            }
            }
        }

        private boolean fillCollection () {
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
