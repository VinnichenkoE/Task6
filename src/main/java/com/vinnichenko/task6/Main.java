package com.vinnichenko.task6;

import com.vinnichenko.task6.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.entity.Author;
import com.vinnichenko.task6.entity.Book;
import com.vinnichenko.task6.entity.Warehouse;
import com.vinnichenko.task6.exception.WarehouseException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws WarehouseException {
        Author author = new Author("name", "surname");
        Author author2 = new Author("name2", "surname2");
        Author author3 = new Author("name3", "surname3");
        List<Author> authors1 = new ArrayList<>();
        authors1.add(author);
        List<Author> authors2 = new ArrayList<>();
        authors2.add(author2);
        authors2.add(author3);
        List<Author> authors3 = new ArrayList<>();
        authors3.add(author);
        authors3.add(author3);
        Book book1 = new Book("title1", authors1, 200, "typo1");
        Book book2 = new Book("title2", authors2, 10, "typo2");
        Book book3 = new Book("title1", authors3, 15, "typo3");
        Warehouse.getInstance().addBook(book1);
        Warehouse.getInstance().addBook(book2);
        Warehouse.getInstance().addBook(book3);
        System.out.println(Warehouse.getInstance());
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        System.out.println(bookListDao.sortBooksById());
        System.out.println(Warehouse.getInstance());
    }
}
