package com.vinnichenko.task6.controller.command.impl;

import com.vinnichenko.task6.controller.RequestParameters;
import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.entity.CustomBook;
import com.vinnichenko.task6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByNumberPagesCommand implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String numberPages = parameters
                .get(RequestParameters.PARAMETER_NUMBER_PAGES);
        Map<String, String> response = new HashMap<>();
        try {
            List<CustomBook> books = service.findByNumberPages(numberPages);
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.SUCCESS_STATUS);
            response.put(ResponseParameters.RESULT, books.toString());
        } catch (ServiceException e) {
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.FAIL_STATUS);
            response.put(ResponseParameters.MESSAGE, e.getMessage());
        }
        return response;
    }
}