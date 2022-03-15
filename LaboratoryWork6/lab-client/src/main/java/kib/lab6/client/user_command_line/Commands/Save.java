package kib.lab6.client.user_command_line.Commands;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.csv_parser.CSVSaver;
import kib.lab6.client.user_command_line.ErrorMessage;
import kib.lab6.client.user_command_line.SuccessMessage;

import java.io.IOException;

public class Save extends AbstractCommand {

    private static final int AMOUNT_OF_ARGS = 0;
    private final String filePath;

    public Save(String filePath) {
        super("save", "Сохранить коллекцию в файл", AMOUNT_OF_ARGS);
        this.filePath = filePath;
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAmountOfArgs()) {
            try {
                CSVSaver saver = new CSVSaver(this.filePath);
                saver.saveToFile(Config.getCollectionManager().getArrayOfInfo());
                return new SuccessMessage("Коллекция успешно сохранена");
            } catch (IOException e) {
                return new ErrorMessage("Файл не найден");
            }
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
