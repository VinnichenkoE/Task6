package com.vinnichenko.task6.controller;

import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.CommandProvider;

import java.util.Map;

public class WarehouseController {
    public Map<String, String> executeTask(Map<String, String> parameters) {
        String commandName = parameters.get("commandName");
        CommandProvider provider = new CommandProvider();
        Command command = provider.getCommand(commandName);
        return command.execute(parameters);
    }
}
