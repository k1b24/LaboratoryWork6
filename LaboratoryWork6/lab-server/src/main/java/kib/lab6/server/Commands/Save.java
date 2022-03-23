package kib.lab6.server.Commands;

import kib.lab6.common.util.Request;
import kib.lab6.server.abstractions.AbstractCommand;

/**
 * Серверная команда для сохранения коллекции в файл
 */
public class Save extends AbstractCommand {

    public Save() {
        super("save", "Сохранить коллекцию в файл", true);
    }

    @Override
    public Object execute(Request request) {
        return null;//TODO Придумать реализацию
    }
}
