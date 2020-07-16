package com.vinnichenko.task6.model.dao;

import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.Book;
import com.vinnichenko.task6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {

    boolean add(Book book) throws DaoException;

    void remove(Book book) throws DaoException;

    Optional<Book> findById(String id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findByNumberPages(int numberPages);

    List<Book> findByTypography(String typography);

    List<Book> sortBooksById();

    List<Book> sortBooksByTitle();

    List<Book> sortBooksByAuthor();

    List<Book> sortBooksByPages();

    List<Book> sortBooksByTypography();
}
