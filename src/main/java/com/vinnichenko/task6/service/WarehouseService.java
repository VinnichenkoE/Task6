package com.vinnichenko.task6.service;

import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    boolean addBook(String title, List<Author> author, int numberPages, String typography) throws ServiceException;

    Optional<Book> findBYId(String id) throws ServiceException;

    void removeBook(String id) throws ServiceException;
}
