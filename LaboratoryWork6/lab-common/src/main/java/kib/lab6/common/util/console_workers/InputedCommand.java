package kib.lab6.common.util.console_workers;

public class InputedCommand {

    private String name;
    private String[] arguments;

    public InputedCommand(String name, String[] arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public String[] getArguments() {
        return arguments;
    }
}
