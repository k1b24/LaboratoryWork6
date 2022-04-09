package kib.lab6.server.utils;

import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.server.Commands.Save;
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
        commands.put("save", new Save());
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

    public Object executeCommandFromRequest(Request requestFromClient) {
        Object response;
        if (commands.containsKey(requestFromClient.getCommandNameToSend().toLowerCase())) {
            if (!requestFromClient.isServerRequest() && !commands.get(requestFromClient.getCommandNameToSend().toLowerCase()).isOnlyServerCommand()) {
                try {
                    appendCommandToHistory(requestFromClient.getCommandNameToSend());
                    response = commands.get(requestFromClient.getCommandNameToSend()).execute(requestFromClient);
                } catch (IllegalArgumentException e) {
                    response = new Response(new ErrorMessage(e.getMessage()));
                }
            } else if (requestFromClient.isServerRequest()) {
                try {
                    appendCommandToHistory(requestFromClient.getCommandNameToSend());
                    response =  commands.get(requestFromClient.getCommandNameToSend()).execute(requestFromClient);
                } catch (IllegalArgumentException e) {
                    response =  new Response(new ErrorMessage(e.getMessage()));
                }
            } else {
                response =  new Response(new ErrorMessage("Такая команда недоступна"));
            }
        } else {
            response =  new Response(new ErrorMessage("Такая команда отсутствует"));
        }
        return response;
    }

    public ArrayDeque<AbstractCommand> getLastExecutedCommands() {
        return lastExecutedCommands;
    }

    private void appendCommandToHistory(String name) {
        lastExecutedCommands.addFirst(commands.get(name));
        if (lastExecutedCommands.size() == AMOUNT_OF_COMMANDS_TO_SAVE + 1) {
            lastExecutedCommands.pollLast();
        }
    }
}
