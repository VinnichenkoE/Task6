package com.vinnichenko.task6.model.entity;

import com.vinnichenko.task6.exception.WarehouseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warehouse {

    private static final Warehouse INSTANCE = new Warehouse();
    private List<CustomBook> customBooks = new ArrayList<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public List<CustomBook> getCustomBooks() {
        return Collections.unmodifiableList(customBooks);
    }

    public boolean addBook(CustomBook customBook) throws WarehouseException {
        if (customBooks.contains(customBook)) {
            throw new WarehouseException("such book already exist");
        }
        return customBooks.add(customBook);
    }

    public void removeBook(CustomBook customBook) throws WarehouseException {
        if (!customBooks.contains(customBook)) {
            throw new WarehouseException("no such book in warehouse");
        }
        customBooks.remove(customBook);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{");
        sb.append("books{");
        for (CustomBook customBook : customBooks) {
            sb.append(customBook.toString()).append("; ");
        }
        sb.append('}');
        return sb.toString();
    }
}