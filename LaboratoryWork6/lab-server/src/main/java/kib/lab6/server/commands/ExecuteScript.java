package kib.lab6.server.commands;

import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.common.util.client_server_communication.Request;

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
