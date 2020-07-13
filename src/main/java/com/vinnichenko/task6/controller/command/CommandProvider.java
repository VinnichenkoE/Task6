package com.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.command.impl.AddBook;
import com.vinnichenko.task6.controller.command.impl.FindById;
import com.vinnichenko.task6.controller.command.impl.RemoveBook;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.REMOVE_BOOK, new RemoveBook());
        repository.put(CommandName.FIND_BY_ID, new FindById());
    }

    public Command getCommand(String commandName) {
        return repository.get(CommandName.valueOf(commandName.toUpperCase()));
    }
}
