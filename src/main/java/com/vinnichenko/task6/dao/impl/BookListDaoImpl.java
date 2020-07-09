package com.vinnichenko.task6.dao.impl;

import com.vinnichenko.task6.dao.BookListDao;
import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.entity.Warehouse;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.WarehouseException;

import java.util.*;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {
    @Override
    public boolean addBook(Book book) throws DaoException {
        if (book == null) {
            throw new DaoException("such book does not exist");
        }
        boolean result;
        try {
            result = Warehouse.getInstance().addBook(book);
        } catch (WarehouseException e) {
            throw new DaoException("such book already exist", e);
        }
        return result;
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        if (book == null) {
            throw new DaoException("such book does not exist");
        }
        try {
            Warehouse.getInstance().removeBook(book);
        } catch (WarehouseException e) {
            throw new DaoException("no such book in repository", e);
        }
    }

    @Override
    public Optional<Book> findById(String id) {
        List<Book> books = Warehouse.getInstance().getRepository();
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = Warehouse.getInstance().getRepository();
        return books.stream().filter(book -> book.getTitle().equals(title)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        List<Book> books = Warehouse.getInstance().getRepository();
        return books.stream().filter(book -> book.getAuthors().contains(author)).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByNumberPages(int numberPages) {
        List<Book> books = Warehouse.getInstance().getRepository();
        return books.stream().filter(book -> book.getNumberPages() == numberPages).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTypography(String typography) {
        List<Book> books = Warehouse.getInstance().getRepository();
        return books.stream().filter(book -> book.getTypography().equals(typography)).collect(Collectors.toList());
    }

    @Override
    public List<Book> sortBooksById() {
        List<Book> books = Warehouse.getInstance().getRepository();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(book -> book.getId()));
        return copy;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        List<Book> books = Warehouse.getInstance().getRepository();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(book -> book.getTitle()));
        return copy;
    }

    @Override
    public List<Book> sortBooksByAuthor() {
        return null;
    }

    @Override
    public List<Book> sortBooksByPages() {
        List<Book> books = Warehouse.getInstance().getRepository();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(book -> book.getNumberPages()));
        return copy;
    }

    @Override
    public List<Book> sortBooksByTypography() {
        List<Book> books = Warehouse.getInstance().getRepository();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(book -> book.getTypography()));
        return copy;
    }
}
