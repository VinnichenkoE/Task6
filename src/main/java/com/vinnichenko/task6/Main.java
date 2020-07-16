package com.vinnichenko.task6;

import com.vinnichenko.task6.exception.WarehouseException;
import com.vinnichenko.task6.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.Book;
import com.vinnichenko.task6.model.entity.Warehouse;

import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws WarehouseException {
        Author author = new Author("name", "surname");
        Author author2 = new Author("name2", "surname2");
        Author author3 = new Author("name3", "surname3");
        Set<Author> authors1 = new LinkedHashSet<>();
        authors1.add(author);
        Set<Author> authors2 = new LinkedHashSet<>();
        authors2.add(author2);
        authors2.add(author3);
        Set<Author> authors3 = new LinkedHashSet<>();
        authors3.add(author);
        authors3.add(author3);
        Book book1 = new Book("title1", authors2, 200, "typo1");
        Book book2 = new Book("title2", authors1, 10, "typo2");
        Book book3 = new Book("title1", authors3, 15, "typo3");
        Warehouse.getInstance().addBook(book1);
        Warehouse.getInstance().addBook(book2);
        Warehouse.getInstance().addBook(book3);
        System.out.println(Warehouse.getInstance().getRepository());
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        System.out.println(bookListDao.sortBooksByAuthor());
        System.out.println(Warehouse.getInstance().getRepository());
    }
}
