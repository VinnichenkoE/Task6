package test.vinnichenko.task6.dataprovider;

import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import org.testng.annotations.DataProvider;

import java.util.*;

public class SortDataProvider {

    private static CustomBook book;
    private static CustomBook book2;
    private static CustomBook book3;
    private static CustomBook book4;

    static {
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lev", "Tolstoy");
        authors.add(author);
        book = new CustomBook("4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe",
                "War and Peace", authors, 581,
                "Konica Minolta");
        Set<Author> authors2 = new HashSet<>();
        Author author2 = new Author("Alexander", "Pushkin");
        authors2.add(author2);
        book2 = new CustomBook("4a5f2a66-2035-4b28-ba3a-bf08fcda0ebe",
                "Evgeniy Onegin", authors2, 250,
                "Moscow");
        Set<Author> authors3 = new HashSet<>();
        Author author3 = new Author("Ivan", "Tyrgenev");
        authors3.add(author3);
        book3 = new CustomBook("4a5f2a66-2034-4b28-ba3a-bf08fcda0ebe",
                "Hunter's Notes", authors3, 125,
                "Contemporary");
        Set<Author> authors4 = new HashSet<>();
        Author author4 = new Author("Mihail", "Bylgokov");
        authors4.add(author4);
        book4 = new CustomBook("4a5f2a66-2033-4b28-ba3a-bf08fcda0ebe",
                "Master and Margarita", authors4, 350,
                "Tver");
    }

    @DataProvider(name = "sortById")
    public static Object[][] createDataSortById() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book4);
        books.add(book3);
        books.add(book2);
        books.add(book);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByTitle")
    public static Object[][] createDataSortByTitle() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByAuthor")
    public static Object[][] createDataSortByAuthor() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book4);
        books.add(book2);
        books.add(book);
        books.add(book3);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByNumberPages")
    public static Object[][] createDataSortByNumberPages() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book3);
        books.add(book2);
        books.add(book4);
        books.add(book);
        return new Object[][]{{books}};
    }

    @DataProvider(name = "sortByTypography")
    public static Object[][] createDataSortByTypography() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book3);
        books.add(book);
        books.add(book2);
        books.add(book4);
        return new Object[][]{{books}};
    }
}
