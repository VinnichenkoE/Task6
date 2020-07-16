package com.vinnichenko.task6.model.service;

import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WarehouseService {

    boolean addBook(String title, Set<Author> author, int numberPages, String typography) throws ServiceException;

    Book findBYId(String id) throws ServiceException;

    void removeBook(String id) throws ServiceException;

    List<Book> findByTitle(String title) throws ServiceException;

    List<Book> findByAuthor(Author author) throws ServiceException;

    List<Book> findByNumberPages(int numberPages) throws ServiceException;

    List<Book> findByTypography(String typography) throws ServiceException;

    List<Book> sortBooksById();

    List<Book> sortBooksByTitle();

    List<Book> sortBooksByAuthor();

    List<Book> sortBooksByPages();

    List<Book> sortBooksByTypography();
}
