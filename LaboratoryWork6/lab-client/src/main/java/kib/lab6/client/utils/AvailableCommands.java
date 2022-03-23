package kib.lab6.client.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AvailableCommands {
    public static final Set<String> commandsWithoutArguments = new HashSet<String>() {
        {
            add("clear");
            add("head");
            add("info");
            add("pring_descending");
            add("show");
            add("help");
            add("history");
        }
    };

    public static final Set<String> commandsWithHumanBeingArgument = new HashSet<String>() {
        {
            add("add");
            add("add_if_min");
        }
    };

    public static final Set<String> commandsWithNumberArgument = new HashSet<String>() {
        {
            add("filter_less_than_car");
            add("remove_by_id");
        }
    };

    public static final Set<String> commandsWithMoodArgument = new HashSet<String>() {
        {
            add("remove_by_any_mood");
        }
    };

    public static final Set<String> commandsWithHumanBeingAndNumberArguments = new HashSet<String>() {
        {
            add("update");
        }
    };

    public static final Set<String> scriptArgumentCommands = new HashSet<String>() {
        {
            add("execute_script");
        }
    };
}
