package kib.lab6.server.abstractions;


import kib.lab6.common.util.client_server_communication.Request;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    private static final List<AbstractCommand> CLIENT_COMMANDS_LIST = new ArrayList<>();
    private final String name;
    private final String description;
    private final boolean onlyServerCommand;


    public AbstractCommand(String name, String description, boolean onlyServerCommand) {
        this.name = name;
        this.description = description;
        this.onlyServerCommand = onlyServerCommand;
        if (!onlyServerCommand) {
            CLIENT_COMMANDS_LIST.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isOnlyServerCommand() {
        return onlyServerCommand;
    }

    public List<AbstractCommand> getCommandsList() {
        return CLIENT_COMMANDS_LIST;
    }

    public String getDescription() {
        return name + ": " + description;
    }

    public abstract Object execute(Request request);
}
