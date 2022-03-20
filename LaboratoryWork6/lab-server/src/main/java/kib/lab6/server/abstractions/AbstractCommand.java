package kib.lab6.server.abstractions;


import kib.lab6.common.util.Request;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    private static final List<AbstractCommand> COMMANDS_LIST = new ArrayList<>();
    private final String name;
    private final String description;


    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
        COMMANDS_LIST.add(this);
    }

    public String getName() {
        return name;
    }

    public List<AbstractCommand> getCommandsList() {
        return COMMANDS_LIST;
    }

    public String getDescription() {
        return name + ": " + description;
    }

    public abstract Object execute(Request request);
}
