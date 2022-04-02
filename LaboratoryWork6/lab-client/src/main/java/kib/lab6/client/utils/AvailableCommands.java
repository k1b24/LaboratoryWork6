package kib.lab6.client.utils;

import java.util.HashSet;
import java.util.Set;

public final class AvailableCommands {

    public static final Set<String> COMMANDS_WITHOUT_ARGUMENTS = new HashSet<>();

    public static final Set<String> COMMANDS_WITH_HUMAN_BEING_ARGUMENT = new HashSet<>();

    public static final Set<String> COMMANDS_WITH_NUMBER_ARGUMENT = new HashSet<>();

    public static final Set<String> COMMANDS_WITH_MOOD_ARGUMENT = new HashSet<>();

    public static final Set<String> COMMANDS_WITH_HUMAN_BEING_AND_NUMBER_ARGUMENTS = new HashSet<>();

    static {
        COMMANDS_WITHOUT_ARGUMENTS.add("clear");
        COMMANDS_WITHOUT_ARGUMENTS.add("head");
        COMMANDS_WITHOUT_ARGUMENTS.add("info");
        COMMANDS_WITHOUT_ARGUMENTS.add("print_descending");
        COMMANDS_WITHOUT_ARGUMENTS.add("show");
        COMMANDS_WITHOUT_ARGUMENTS.add("help");
        COMMANDS_WITHOUT_ARGUMENTS.add("history");
        COMMANDS_WITH_HUMAN_BEING_ARGUMENT.add("add");
        COMMANDS_WITH_HUMAN_BEING_ARGUMENT.add("add_if_min");
        COMMANDS_WITH_NUMBER_ARGUMENT.add("filter_less_than_car");
        COMMANDS_WITH_NUMBER_ARGUMENT.add("remove_by_id");
        COMMANDS_WITH_MOOD_ARGUMENT.add("remove_by_any_mood");
        COMMANDS_WITH_HUMAN_BEING_AND_NUMBER_ARGUMENTS.add("update");
    }

    private AvailableCommands() {

    }
}
