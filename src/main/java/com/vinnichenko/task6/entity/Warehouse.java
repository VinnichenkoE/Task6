package com.vinnichenko.task6.entity;

import com.vinnichenko.task6.exception.WarehouseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warehouse {

    private static final Warehouse INSTANCE = new Warehouse();
    private List<Book> books = new ArrayList<>();

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public List<Book> getRepository() {
        return Collections.unmodifiableList(books);
    }

    public boolean addBook(Book book) throws WarehouseException {
        if (books.contains(book)) {
            throw new WarehouseException("such book already exist");
        }
        return books.add(book);
    }

    public void removeBook(Book book) throws WarehouseException {
        if (!books.contains(book)) {
            throw new WarehouseException("no such book in repository");
        }
        books.remove(book);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warehouse{");
        sb.append("books{");
        for (Book book: books) {
            sb.append(book.toString()).append("; ");
        }
        sb.append('}');
        return sb.toString();
    }
}

