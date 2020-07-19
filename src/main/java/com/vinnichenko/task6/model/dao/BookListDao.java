package com.vinnichenko.task6.model.dao;

import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import com.vinnichenko.task6.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {

    boolean add(CustomBook customBook) throws DaoException;

    void remove(CustomBook customBook) throws DaoException;

    Optional<CustomBook> findById(String id);

    List<CustomBook> findByTitle(String title);

    List<CustomBook> findByAuthor(Author author);

    List<CustomBook> findByNumberPages(int numberPages);

    List<CustomBook> findByTypography(String typography);

    List<CustomBook> sortBooksById();

    List<CustomBook> sortBooksByTitle();

    List<CustomBook> sortBooksByAuthor();

    List<CustomBook> sortBooksByPages();

    List<CustomBook> sortBooksByTypography();
}
