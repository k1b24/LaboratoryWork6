package kib.lab6.server.Commands;

import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.Request;

public class ExecuteScript extends AbstractCommand {

    public ExecuteScript() {
        super("execute_script", "Выполнить скрипт из файла, принимает"
                + " на вход один аргумент [file_path]", false);
    }

    @Override
    public Object execute(Request request) {
        return null;
    }
}
