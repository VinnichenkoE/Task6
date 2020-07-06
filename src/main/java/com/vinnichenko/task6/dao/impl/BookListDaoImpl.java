package com.vinnichenko.task6.dao.impl;

import com.vinnichenko.task6.dao.BookListDao;
import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.WarehouseException;
import com.vinnichenko.task6.warehouse.Warehouse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookListDaoImpl implements BookListDao {
    @Override
    public boolean addBook(Book book) throws DaoException {
        Warehouse warehouse = Warehouse.getInstance();
        boolean result;
        try {
            result = warehouse.addBook(book);
        } catch (WarehouseException e) {
            throw new DaoException("such book already exist", e);
        }
        return result;
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        Warehouse warehouse = Warehouse.getInstance();
        try {
            warehouse.removeBook(book);
        } catch (WarehouseException e) {
            throw new DaoException("no such book in repository", e);
        }
    }

    @Override
    public Optional<Book> findById(String id) {
        Warehouse warehouse = Warehouse.getInstance();
        List<Book> books = warehouse.getRepository();
        UUID uuid = UUID.fromString(id);
        for (Book book : books) {
            if (book.getId().equals(uuid)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> findByNumberPages(int numberPages) {
        return null;
    }

    @Override
    public List<Book> findByTypography(String typography) {
        return null;
    }

    @Override
    public List<Book> sortBooksById() {
        return null;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        return null;
    }

    @Override
    public List<Book> sortBooksByAuthor() {
        return null;
    }

    @Override
    public List<Book> sortBooksByPages() {
        return null;
    }

    @Override
    public List<Book> sortBooksByTypography() {
        return null;
    }
}
