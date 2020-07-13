package com.vinnichenko.task6.service.impl;

import com.vinnichenko.task6.dao.BookListDao;
import com.vinnichenko.task6.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.service.WarehouseService;
import com.vinnichenko.task6.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class WarehouseServiceImpl implements WarehouseService {
    @Override
    public boolean addBook(String title, List<Author> authors, int numberPages, String typography) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isNumberPagesValid(numberPages) || !bookValidator.isTitleValid(title)) {
            throw new ServiceException("incorrect parameters");
        }
        Book book = new Book(title, authors, numberPages, typography);
        BookListDao bookListDao = new BookListDaoImpl();
        boolean result;
        try {
            result = bookListDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException("can not add book", e);
        }
        return result;
    }

    @Override
    public Optional<Book> findBYId(String id) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdValid(id)) {
            throw new ServiceException("incorrect value of id");
        }
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.findById(id);
    }

    @Override
    public void removeBook(String id) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        if (!bookValidator.isIdValid(id)) {
            throw new ServiceException("incorrect value of id");
        }
        BookListDao bookListDao = new BookListDaoImpl();
        if (!bookListDao.findById(id).isPresent()) {
            throw new ServiceException("no such book in warehouse");
        }
        Book book = bookListDao.findById(id).get();
        try {
            bookListDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException("can not remove book", e);
        }
    }
}
