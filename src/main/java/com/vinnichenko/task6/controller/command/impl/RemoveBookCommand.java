package com.vinnichenko.task6.controller.command.impl;

import com.vinnichenko.task6.controller.RequestParameters;
import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class RemoveBookCommand implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        BookServiceImpl service = BookServiceImpl.getInstance();
        String id = parameters.get(RequestParameters.ID);
        Map<String, String> response = new HashMap<>();
        try {
            service.removeBook(id);
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.SUCCESS_STATUS);
        } catch (ServiceException e) {
            response.put(ResponseParameters.STATUS,
                    ResponseParameters.FAIL_STATUS);
            response.put(ResponseParameters.MESSAGE, e.getMessage());
        }
        return response;
    }
}
