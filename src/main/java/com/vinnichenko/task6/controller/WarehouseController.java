package com.vinnichenko.task6.controller;

import com.vinnichenko.task6.controller.command.Command;

public class WarehouseController {
    public String executeTask(String request) {
        String[] parameters = request.split("\\s+");
        String commandName = parameters[0];
        CommandProvider provider = new CommandProvider();
        Command command = provider.getCommand(commandName);
        return command.execute(request);
    }
}
