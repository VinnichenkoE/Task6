package com.vinnichenko.task6.model.service;

import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.entity.CustomBook;

import java.util.List;

public interface BookService {

    boolean add(String title, String author,
                String numberPages, String typography)
            throws ServiceException;

    CustomBook findById(String id) throws ServiceException;

    void removeBook(String id) throws ServiceException;

    List<CustomBook> findByTitle(String title) throws ServiceException;

    List<CustomBook> findByAuthor(String author) throws ServiceException;

    List<CustomBook> findByNumberPages(String numberPages)
            throws ServiceException;

    List<CustomBook> findByTypography(String typography)
            throws ServiceException;

    List<CustomBook> sortBooksById();

    List<CustomBook> sortBooksByTitle();

    List<CustomBook> sortBooksByAuthor();

    List<CustomBook> sortBooksByPages();

    List<CustomBook> sortBooksByTypography();
}
