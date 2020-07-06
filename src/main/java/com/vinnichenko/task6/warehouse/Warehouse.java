package com.vinnichenko.task6.warehouse;

import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.exception.WarehouseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warehouse {

    private static final Warehouse INSTANCE = new Warehouse();
    private List<Book> repository = new ArrayList<>();

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public List<Book> getRepository() {
        return Collections.unmodifiableList(repository);
    }

    public boolean addBook(Book book) throws WarehouseException {
        if (repository.contains(book)) {
            throw new WarehouseException("such book already exist");
        }
        return repository.add(book);
    }

    public void removeBook(Book book) throws WarehouseException {
        if (!repository.contains(book)) {
            throw new WarehouseException("no such book in repository");
        }
        repository.remove(book);
    }
}

