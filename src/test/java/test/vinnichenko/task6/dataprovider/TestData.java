package test.vinnichenko.task6.dataprovider;

import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.model.dao.BookListDao;
import com.vinnichenko.task6.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;

import java.util.HashSet;
import java.util.Set;

public class TestData {

    public static void fillIn() throws DaoException {
        BookListDao bookListDao = BookListDaoImpl.getInstance();
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lev", "Tolstoy");
        authors.add(author);
        CustomBook book =
                new CustomBook("4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe",
                        "War and Peace", authors, 581,
                        "Konica Minolta");
        Set<Author> authors2 = new HashSet<>();
        Author author2 = new Author("Alexander", "Pushkin");
        authors2.add(author2);
        CustomBook book2 =
                new CustomBook("4a5f2a66-2035-4b28-ba3a-bf08fcda0ebe",
                        "Evgeniy Onegin", authors2, 250,
                        "Moscow");
        Set<Author> authors3 = new HashSet<>();
        Author author3 = new Author("Ivan", "Tyrgenev");
        authors3.add(author3);
        CustomBook book3 =
                new CustomBook("4a5f2a66-2034-4b28-ba3a-bf08fcda0ebe",
                        "Hunter's Notes", authors3, 125,
                        "Contemporary");
        Set<Author> authors4 = new HashSet<>();
        Author author4 = new Author("Mihail", "Bylgokov");
        authors4.add(author4);
        CustomBook book4 =
                new CustomBook("4a5f2a66-2033-4b28-ba3a-bf08fcda0ebe",
                        "Master and Margarita", authors4, 350,
                        "Tver");
        bookListDao.add(book);
        bookListDao.add(book2);
        bookListDao.add(book3);
        bookListDao.add(book4);
    }
}
