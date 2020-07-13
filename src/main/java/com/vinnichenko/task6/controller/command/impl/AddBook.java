package com.vinnichenko.task6.controller.command.impl;

import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.parser.AuthorParser;
import com.vinnichenko.task6.service.impl.WarehouseServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBook implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        WarehouseServiceImpl service = new WarehouseServiceImpl();
        String title = parameters.get("title");
        String stringAuthors = parameters.get("authors");
        AuthorParser parser = new AuthorParser();
        List<Author> authorList = parser.stringToAuthor(stringAuthors);
        String stringNumberPages = parameters.get("numberPages");
        int numberPages = Integer.parseInt(stringNumberPages);
        String typography = parameters.get("typography");
        Map<String, String> response = new HashMap<>();
        try {
            service.addBook(title, authorList, numberPages, typography);
            response.put("Status", "Success");
        } catch (ServiceException e) {
            response.put("Status", "Fail");
        }
        return response;
    }
}
