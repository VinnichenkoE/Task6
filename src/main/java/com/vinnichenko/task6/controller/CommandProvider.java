package com.vinnichenko.task6.controller;

import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.impl.AddBook;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.ADD_BOOK, new AddBook());
    }

    Command getCommand(String commandName) {
        return repository.get(CommandName.valueOf(commandName.toUpperCase()));
    }

    private enum CommandName {
        ADD_BOOK, FIND_BY_ID
    }
}
