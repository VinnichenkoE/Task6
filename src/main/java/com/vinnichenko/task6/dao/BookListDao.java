package com.vinnichenko.task6.dao;

import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookListDao {

    boolean addBook(Book book) throws DaoException;

    void removeBook(Book book) throws DaoException;

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
