package com.vinnichenko.task6.controller.command;

import java.util.Map;

public interface Command {
    Map<String, String> execute(Map<String, String> parameters);
}
