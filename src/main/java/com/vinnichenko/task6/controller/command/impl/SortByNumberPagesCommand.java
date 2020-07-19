package com.vinnichenko.task6.controller.command.impl;

import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.model.entity.CustomBook;
import com.vinnichenko.task6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByNumberPagesCommand implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        Map<String, String> response = new HashMap<>();
        List<CustomBook> books = service.sortBooksByPages();
        response.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        response.put(ResponseParameters.RESULT, books.toString());
        return response;
    }
}
