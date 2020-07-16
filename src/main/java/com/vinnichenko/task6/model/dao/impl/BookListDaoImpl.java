package com.vinnichenko.task6.model.dao.impl;

import com.vinnichenko.task6.model.dao.BookListDao;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.Book;
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
    public boolean add(Book book) throws DaoException {
        boolean result;
        try {
            result = Warehouse.getInstance().addBook(book);
        } catch (WarehouseException e) {
            throw new DaoException("can not add book", e);
        }
        return result;
    }

    @Override
    public void remove(Book book) throws DaoException {
        try {
            Warehouse.getInstance().removeBook(book);
        } catch (WarehouseException e) {
            throw new DaoException("can not remove book", e);
        }
    }

    @Override
    public Optional<Book> findById(String id) {
        List<Book> books = Warehouse.getInstance().getBooks();
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> result = books.stream()
                .filter(b -> b.getTitle().equals(title))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> result = books.stream()
                .filter(b -> b.getAuthors().contains(author))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> findByNumberPages(int numberPages) {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> result = books.stream()
                .filter(b -> b.getNumberPages() == numberPages)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> findByTypography(String typography) {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> result = books.stream()
                .filter(b -> b.getTypography().equals(typography))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Book> sortBooksById() {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(Book::getId));
        return copy;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(Book::getTitle));
        return copy;
    }

    @Override
    public List<Book> sortBooksByAuthor() {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing
                (b -> b.getAuthors().stream().findFirst().get()));
        return copy;
    }

    @Override
    public List<Book> sortBooksByPages() {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(Book::getNumberPages));
        return copy;
    }

    @Override
    public List<Book> sortBooksByTypography() {
        List<Book> books = Warehouse.getInstance().getBooks();
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(Book::getTypography));
        return copy;
    }
}
