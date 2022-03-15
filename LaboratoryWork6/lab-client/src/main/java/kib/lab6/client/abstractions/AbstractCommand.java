package kib.lab6.client.abstractions;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    private static final List<AbstractCommand> COMMANDS_LIST = new ArrayList<>();
    private final String name;
    private final String description;
    private final int amountOfArgs;

    public AbstractCommand(String name, String description, int amountOfArgs) {
        this.name = name;
        this.description = description;
        this.amountOfArgs = amountOfArgs;
        COMMANDS_LIST.add(this);
    }

    public String getName() {
        return name;
    }

    public List<AbstractCommand> getCommandsList() {
        return COMMANDS_LIST;
    }

    public int getAmountOfArgs() {
        return amountOfArgs;
    }

    public String getDescription() {
        return name + ": " + description;
    }

    public abstract Object execute(String[] args);
}
