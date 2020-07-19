package com.vinnichenko.task6.model.service.impl;

import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.dao.BookListDao;
import com.vinnichenko.task6.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import com.vinnichenko.task6.model.service.BookService;
import com.vinnichenko.task6.util.parser.AuthorParser;
import com.vinnichenko.task6.validator.DataValidator;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BookServiceImpl implements BookService {

    private static final BookServiceImpl INSTANCE = new BookServiceImpl();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(String title, String authors,
                       String numberPages, String typography)
            throws ServiceException {
        if (title == null || authors == null || typography == null
                || numberPages == null) {
            throw new ServiceException("incorrect parameters");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isNumberPagesValid(numberPages)
                || !dataValidator.isAuthorValid(authors)) {
            throw new ServiceException("incorrect parameters");
        }
        int intNumberPages = Integer.parseInt(numberPages);
        AuthorParser authorParser = new AuthorParser();
        Set<Author> authorSet = authorParser.stringToAuthor(authors);
        CustomBook customBook = new CustomBook(title, authorSet,
                intNumberPages, typography);
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        boolean result;
        try {
            result = bookListDao.add(customBook);
        } catch (DaoException e) {
            throw new ServiceException("can not add book", e);
        }
        return result;
    }

    @Override
    public CustomBook findById(String id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("incorrect parameter");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isIdValid(id)) {
            throw new ServiceException("incorrect value of id");
        }
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        Optional<CustomBook> optionalBook = bookListDao.findById(id);
        if (!optionalBook.isPresent()) {
            throw new ServiceException("no such book in warehouse");
        }
        CustomBook result = optionalBook.get();
        return result;
    }

    @Override
    public void removeBook(String id) throws ServiceException {
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        CustomBook customBook = findById(id);
        try {
            bookListDao.remove(customBook);
        } catch (DaoException e) {
            throw new ServiceException("can not remove book", e);
        }
    }

    @Override
    public List<CustomBook> findByTitle(String title) throws ServiceException {
        if (title == null) {
            throw new ServiceException("title is null");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.findByTitle(title);
        return result;
    }

    @Override
    public List<CustomBook> findByAuthor(String stringAuthor)
            throws ServiceException {
        if (stringAuthor == null) {
            throw new ServiceException("author is null");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isAuthorValid(stringAuthor)) {
            throw new ServiceException("incorrect parameter");
        }
        List<CustomBook> result;
        AuthorParser authorParser = new AuthorParser();
        Set<Author> authors = authorParser.stringToAuthor(stringAuthor);
        Author author = authors.stream().findFirst().get();
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        result = bookListDao.findByAuthor(author);
        return result;
    }

    @Override
    public List<CustomBook> findByNumberPages(String numberPages)
            throws ServiceException {
        if (numberPages == null) {
            throw new ServiceException("numberPages is null");
        }
        DataValidator dataValidator = new DataValidator();
        if (!dataValidator.isNumberPagesValid(numberPages)) {
            throw new ServiceException("invalid value of parameter");
        }
        int intNumberPages = Integer.parseInt(numberPages);
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.findByNumberPages(intNumberPages);
        return result;
    }

    @Override
    public List<CustomBook> findByTypography(String typography)
            throws ServiceException {
        if (typography == null) {
            throw new ServiceException("typography is null");
        }
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.findByTypography(typography);
        return result;
    }

    @Override
    public List<CustomBook> sortBooksById() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.sortBooksById();
        return result;
    }

    @Override
    public List<CustomBook> sortBooksByTitle() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.sortBooksByTitle();
        return result;
    }

    @Override
    public List<CustomBook> sortBooksByAuthor() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.sortBooksByAuthor();
        return result;
    }

    @Override
    public List<CustomBook> sortBooksByPages() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.sortBooksByPages();
        return result;
    }

    @Override
    public List<CustomBook> sortBooksByTypography() {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<CustomBook> result = bookListDao.sortBooksByTypography();
        return result;
    }
}