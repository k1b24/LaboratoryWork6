package kib.lab6.client.user_command_line;

import kib.lab6.client.Config;
import kib.lab6.client.abstractions.AbstractCommand;
import kib.lab6.client.user_command_line.Commands.Add;
import kib.lab6.client.user_command_line.Commands.AddIfMin;
import kib.lab6.client.user_command_line.Commands.Clear;
import kib.lab6.client.user_command_line.Commands.ExecuteScript;
import kib.lab6.client.user_command_line.Commands.FilterLessThanCar;
import kib.lab6.client.user_command_line.Commands.Head;
import kib.lab6.client.user_command_line.Commands.Help;
import kib.lab6.client.user_command_line.Commands.History;
import kib.lab6.client.user_command_line.Commands.Info;
import kib.lab6.client.user_command_line.Commands.PrintDescending;
import kib.lab6.client.user_command_line.Commands.RemoveByAnyMood;
import kib.lab6.client.user_command_line.Commands.RemoveByID;
import kib.lab6.client.user_command_line.Commands.Save;
import kib.lab6.client.user_command_line.Commands.Show;
import kib.lab6.client.user_command_line.Commands.Update;

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
        commands.put("save", new Save(Config.getFilePath()));
        commands.put("show", new Show());
        commands.put("update", new Update());
        commands.put("help", new Help());
        commands.put("execute_script", new ExecuteScript());
        commands.put("remove_by_id", new RemoveByID());
        commands.put("history", new History());
    }

    public Object execute(String command, String[] args) {
        if (commands.containsKey(command)) {
            lastExecutedCommands.addFirst(commands.get(command));
            if (lastExecutedCommands.size() == AMOUNT_OF_COMMANDS_TO_SAVE) {
                lastExecutedCommands.pollLast();
            }
            return commands.get(command).execute(args);
        } else {
            return new ErrorMessage("Такой команды не существует, введите help для получения справки по командам");
        }
    }

    public ArrayDeque<AbstractCommand> getLastExecutedCommands() {
        return lastExecutedCommands;
    }
}
