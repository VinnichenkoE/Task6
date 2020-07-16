package com.vinnichenko.task6.model.service.impl;

import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.dao.BookListDao;
import com.vinnichenko.task6.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.Book;
import com.vinnichenko.task6.model.service.WarehouseService;
import com.vinnichenko.task6.validator.BookValidator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WarehouseServiceImpl implements WarehouseService {

    private static final WarehouseServiceImpl INSTANCE = new WarehouseServiceImpl();

    private WarehouseServiceImpl() {
    }

    public static WarehouseServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addBook(String title, Set<Author> authors, int numberPages, String typography) throws ServiceException {
        if (title == null || authors == null || authors.isEmpty() || typography == null) {
            throw new ServiceException("parameter is null or empty ");
        }
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isNumberPagesValid(numberPages)) {
            throw new ServiceException("incorrect parameters");
        }
        Book book = new Book(title, authors, numberPages, typography);
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        boolean result;
        try {
            result = bookListDao.add(book);
        } catch (DaoException e) {
            throw new ServiceException("can not add book", e);
        }
        return result;
    }

    @Override
    public Book findBYId(String id) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdValid(id)) {
            throw new ServiceException("incorrect value of id");
        }
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        Optional<Book> optionalBook = bookListDao.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ServiceException("no such book in warehouse");
        }
        Book result = optionalBook.get();
        return result;
    }

    @Override
    public void removeBook(String id) throws ServiceException {
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        Book book = findBYId(id);
        try {
            bookListDao.remove(book);
        } catch (DaoException e) {
            throw new ServiceException("can not remove book", e);
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws ServiceException {
        if (title == null) {
            throw new ServiceException("title is null");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.findByTitle(title);
        return result;
    }

    @Override
    public List<Book> findByAuthor(Author author) throws ServiceException {
        if (author == null) {
            throw new ServiceException("author is null");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.findByAuthor(author);
        return result;
    }

    @Override
    public List<Book> findByNumberPages(int numberPages) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isNumberPagesValid(numberPages)) {
            throw new ServiceException("invalid value of number of pages");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.findByNumberPages(numberPages);
        return result;
    }

    @Override
    public List<Book> findByTypography(String typography) throws ServiceException {
        if (typography == null) {
            throw new ServiceException("typography is null");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.findByTypography(typography);
        return result;
    }

    @Override
    public List<Book> sortBooksById() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.sortBooksById();
        return result;
    }

    @Override
    public List<Book> sortBooksByTitle() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.sortBooksByTitle();
        return result;
    }

    @Override
    public List<Book> sortBooksByAuthor() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.sortBooksByAuthor();
        return result;
    }

    @Override
    public List<Book> sortBooksByPages() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.sortBooksByPages();
        return result;
    }

    @Override
    public List<Book> sortBooksByTypography() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> result = bookListDao.sortBooksByTypography();
        return result;
    }
}
