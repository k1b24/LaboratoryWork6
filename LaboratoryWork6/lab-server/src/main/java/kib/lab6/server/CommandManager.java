package kib.lab6.server;

import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.server.abstractions.AbstractCommand;
import kib.lab6.server.Commands.Add;
import kib.lab6.server.Commands.AddIfMin;
import kib.lab6.server.Commands.Clear;
import kib.lab6.server.Commands.ExecuteScript;
import kib.lab6.server.Commands.FilterLessThanCar;
import kib.lab6.server.Commands.Head;
import kib.lab6.server.Commands.Help;
import kib.lab6.server.Commands.History;
import kib.lab6.server.Commands.Info;
import kib.lab6.server.Commands.PrintDescending;
import kib.lab6.server.Commands.RemoveByAnyMood;
import kib.lab6.server.Commands.RemoveByID;
import kib.lab6.server.Commands.Show;
import kib.lab6.server.Commands.Update;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private static final int AMOUNT_OF_COMMANDS_TO_SAVE = 10;
    private final Map<String, AbstractCommand> commands = new HashMap<>();
    private final ArrayDeque<AbstractCommand> lastExecutedCommands = new ArrayDeque<>();

    public CommandManager() {
        initMap();
    }

    private void initMap() {
        commands.put("add", new Add());
        commands.put("add_if_min", new AddIfMin());
        commands.put("clear", new Clear());
        commands.put("filter_less_than_car", new FilterLessThanCar());
        commands.put("head", new Head());
        commands.put("info", new Info());
        commands.put("print_descending", new PrintDescending());
        commands.put("remove_by_any_mood", new RemoveByAnyMood());
        commands.put("show", new Show());
        commands.put("update", new Update());
        commands.put("help", new Help());
        commands.put("execute_script", new ExecuteScript());
        commands.put("remove_by_id", new RemoveByID());
        commands.put("history", new History());
    }

    public Object execute(Request requestFromClient) {
        lastExecutedCommands.addFirst(commands.get(requestFromClient.getCommandNameToSend()));
        if (lastExecutedCommands.size() == AMOUNT_OF_COMMANDS_TO_SAVE) {
            lastExecutedCommands.pollLast();
        }
        try {
            return commands.get(requestFromClient.getCommandNameToSend()).execute(requestFromClient);
        } catch (IllegalArgumentException e) {
            return new ErrorMessage(e.getMessage());
        }
    }

    public ArrayDeque<AbstractCommand> getLastExecutedCommands() {
        return lastExecutedCommands;
    }
}
