package com.vinnichenko.task6.model.dao.impl;

import com.vinnichenko.task6.model.dao.BookListDao;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import com.vinnichenko.task6.model.entity.Warehouse;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.WarehouseException;

import java.util.*;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {

    private static final BookListDaoImpl INSTANCE = new BookListDaoImpl();

    private BookListDaoImpl() {
    }

    public static BookListDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(CustomBook customBook) throws DaoException {
        boolean result;
        try {
            result = Warehouse.getInstance().addBook(customBook);
        } catch (WarehouseException e) {
            throw new DaoException("can not add book", e);
        }
        return result;
    }

    @Override
    public void remove(CustomBook customBook) throws DaoException {
        try {
            Warehouse.getInstance().removeBook(customBook);
        } catch (WarehouseException e) {
            throw new DaoException("can not remove book", e);
        }
    }

    @Override
    public Optional<CustomBook> findById(String id) {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        for (CustomBook customBook : customBooks) {
            if (customBook.getId().equals(id)) {
                return Optional.of(customBook);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<CustomBook> findByTitle(String title) {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> result = customBooks.stream()
                .filter(b -> b.getTitle().equals(title))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CustomBook> findByAuthor(Author author) {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> result = customBooks.stream()
                .filter(b -> b.getAuthors().contains(author))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CustomBook> findByNumberPages(int numberPages) {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> result = customBooks.stream()
                .filter(b -> b.getNumberPages() == numberPages)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CustomBook> findByTypography(String typography) {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> result = customBooks.stream()
                .filter(b -> b.getTypography().equals(typography))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CustomBook> sortBooksById() {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> copy = new ArrayList<>(customBooks);
        copy.sort(Comparator.comparing(CustomBook::getId));
        return copy;
    }

    @Override
    public List<CustomBook> sortBooksByTitle() {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> copy = new ArrayList<>(customBooks);
        copy.sort(Comparator.comparing(CustomBook::getTitle));
        return copy;
    }

    @Override
    public List<CustomBook> sortBooksByAuthor() {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> copy = new ArrayList<>(customBooks);
        copy.sort(Comparator.comparing
                (b -> b.getAuthors().stream().findFirst().get()));
        return copy;
    }

    @Override
    public List<CustomBook> sortBooksByPages() {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> copy = new ArrayList<>(customBooks);
        copy.sort(Comparator.comparing(CustomBook::getNumberPages));
        return copy;
    }

    @Override
    public List<CustomBook> sortBooksByTypography() {
        List<CustomBook> customBooks = Warehouse.getInstance().getCustomBooks();
        List<CustomBook> copy = new ArrayList<>(customBooks);
        copy.sort(Comparator.comparing(CustomBook::getTypography));
        return copy;
    }
}
