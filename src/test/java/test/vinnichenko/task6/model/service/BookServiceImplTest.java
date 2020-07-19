package test.vinnichenko.task6.model.service;

import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.exception.ServiceException;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import com.vinnichenko.task6.model.service.impl.BookServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task6.dataprovider.SortDataProvider;
import test.vinnichenko.task6.dataprovider.TestData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class BookServiceImplTest {

    BookServiceImpl bookService;

    @BeforeClass
    public void fillInWarehouse() throws DaoException {
        TestData.fillIn();
    }

    @BeforeMethod
    public void setUp() {
        bookService = BookServiceImpl.getInstance();
    }

    @Test(priority = 1)
    public void addTest() throws ServiceException {
        boolean condition = bookService
                .add("War and Peace", "Lev Tolstoy",
                        "581", "Konica Minolta");
        assertTrue(condition);
    }

    @DataProvider(name = "addTestNegative")
    public Object[][] createDataAddNegative() {
        return new Object[][]{
                {null, "Lev Tolstoy", "581", "Konica Minolta"},
                {"War and Peace", null, "581", "Konica Minolta"},
                {"War and Peace", "Lev Tolstoy", null, "Konica Minolta"},
                {"War and Peace", "Lev Tolstoy", "581", null},
                {"War and Peace", "Lev", "581", "Konica Minolta"},
                {"War and Peace", "Lev Tolstoy", "-15", "Konica Minolta"}
        };
    }

    @Test(dataProvider = "addTestNegative",
            expectedExceptions = ServiceException.class)
    public void addTestNegative(String title, String authors,
                                String numberPages, String typography)
            throws ServiceException {
        bookService.add(title, authors, numberPages, typography);
    }

    @DataProvider(name = "book")
    public Object[][] createDataBook() {
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Alexander", "Pushkin");
        authors.add(author);
        CustomBook book =
                new CustomBook("4a5f2a66-2035-4b28-ba3a-bf08fcda0ebe",
                        "Evgeniy Onegin", authors, 250,
                        "Moscow");
        return new Object[][]{{book}};
    }

    @Test(dataProvider = "book")
    public void findByIdTest(CustomBook expected) throws ServiceException {
        CustomBook actual = bookService
                .findById("4a5f2a66-2035-4b28-ba3a-bf08fcda0ebe");
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByIdNegative")
    public Object[][] createDataFindById() {
        return new Object[][]{
                {"123g4567-e89b-12d3-a456-426655440000"},
                {"4a5f2a66-2085-4b28-ba3a-bf08fcda0ebe"},
                {null}
        };
    }

    @Test(expectedExceptions = ServiceException.class,
            dataProvider = "findByIdNegative")
    public void findByIdTestNegative(String id) throws ServiceException {
        bookService.findById(id);
    }

    @Test(dataProvider = "book")
    public void findByTitleTest(CustomBook book) throws ServiceException {
        List<CustomBook> actual = bookService.findByTitle("Evgeniy Onegin");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByTitleTestNegative() throws ServiceException {
        String title = null;
        bookService.findByTitle(title);
    }

    @Test(dataProvider = "book")
    public void findByAuthorTest(CustomBook book) throws ServiceException {
        List<CustomBook> actual = bookService
                .findByAuthor("Alexander Pushkin");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByAuthorNegative")
    public Object[][] createDataFindByAuthor() {
        return new Object[][]{
                {null},
                {"Lev Tolstoy Alexander"}
        };
    }

    @Test(expectedExceptions = ServiceException.class,
            dataProvider = "findByAuthorNegative")
    public void findByAuthorTestNegative(String author) throws ServiceException {
        bookService.findByAuthor(author);
    }

    @Test(dataProvider = "book")
    public void findByNumberPagesTest(CustomBook book) throws ServiceException {
        List<CustomBook> actual = bookService.findByNumberPages("250");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "findByNumberPagesNegative")
    public Object[][] createDataFindByNumberPages() {
        return new Object[][]{
                {"-15"},
                {"1f3"},
                {null}
        };
    }

    @Test(dataProvider = "findByNumberPagesNegative",
            expectedExceptions = ServiceException.class)
    public void findByNumberPagesTestNegative(String numberPages)
            throws ServiceException {
        bookService.findByNumberPages(numberPages);
    }

    @Test(dataProvider = "book")
    public void findByTypographyTest(CustomBook book) throws ServiceException {
        List<CustomBook> actual = bookService.findByTypography("Moscow");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findByTypographyTestNegative() throws ServiceException {
        bookService.findByTypography(null);
    }

    @Test(dataProvider = "sortById",
            dataProviderClass = SortDataProvider.class)
    public void sortBooksByIdTest(List<CustomBook> expected) {
        List<CustomBook> actual = bookService.sortBooksById();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByTitle(List<CustomBook> expected) {
        List<CustomBook> actual = bookService.sortBooksByTitle();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByAuthor(List<CustomBook> expected) {
        List<CustomBook> actual = bookService.sortBooksByAuthor();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByNumberPages",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByPages(List<CustomBook> expected) {
        List<CustomBook> actual = bookService.sortBooksByPages();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTypography",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByTypography(List<CustomBook> expected) {
        List<CustomBook> actual = bookService.sortBooksByTypography();
        assertEquals(actual, expected);
    }
}