package com.vinnichenko.task6.controller.command.impl;

import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.service.impl.WarehouseServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class RemoveBook implements Command {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        WarehouseServiceImpl service = WarehouseServiceImpl.getInstance();
        String id = parameters.get("id");
        Map<String, String> response = new HashMap<>();
        try {
            service.removeBook(id);
            response.put("Status", "Success");
        } catch (ServiceException e) {
            response.put("Status", "Fail");
        }
        return response;
    }
}
