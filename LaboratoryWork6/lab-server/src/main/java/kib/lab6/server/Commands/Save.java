package kib.lab6.server.Commands;

import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.console_workers.SuccessMessage;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.server.csv_parser.CSVSaver;
import kib.lab6.server.utils.Config;

import java.io.IOException;

/**
 * Серверная команда для сохранения коллекции в файл
 */
public class Save extends AbstractCommand {

    public Save() {
        super("save", "Сохранить коллекцию в файл", true);
    }

    @Override
    public Object execute(Request request) {
        CSVSaver saver = new CSVSaver(Config.getFilePath());
        try {
            saver.saveToFile(Config.getCollectionManager().getArrayOfInfoForSavingToCSV());
            return new Response(new SuccessMessage("Коллекция успешно сохранена в файл"));
        } catch (IOException e) {
            return new Response(new ErrorMessage(e.getMessage()));
        }
    }
}
