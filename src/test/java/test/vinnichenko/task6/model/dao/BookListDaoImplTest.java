package test.vinnichenko.task6.model.dao;

import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.model.dao.impl.BookListDaoImpl;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task6.dataprovider.SortDataProvider;
import test.vinnichenko.task6.dataprovider.TestData;

import java.util.*;

import static org.testng.Assert.*;

public class BookListDaoImplTest {

    BookListDaoImpl bookListDao;

    @BeforeClass
    public void fillInWarehouse() throws DaoException {
        TestData.fillIn();
    }

    @BeforeMethod
    public void setUp() {
        bookListDao = BookListDaoImpl.getInstance();
    }

    @DataProvider(name = "addData")
    public Object[][] createDataAdd() {
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lev", "Tolstoy");
        authors.add(author);
        CustomBook book =
                new CustomBook("War and Peace", authors, 581,
                        "Konica Minolta");
        return new Object[][]{{book}};
    }

    @Test(dataProvider = "addData", priority = 1)
    public void addTest(CustomBook book) throws DaoException {
        boolean condition = bookListDao.add(book);
        assertTrue(condition);
    }

    @DataProvider(name = "addDataNegative")
    public Object[][] createDataAddNegative() {
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lev", "Tolstoy");
        authors.add(author);
        CustomBook book =
                new CustomBook("4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe",
                        "War and Peace", authors, 581,
                        "Konica Minolta");
        return new Object[][]{{book}};
    }

    @Test(dataProvider = "addDataNegative",
            expectedExceptions = DaoException.class)
    public void addTestNegative(CustomBook book) throws DaoException {
        bookListDao.add(book);
    }

    @Test(dataProvider = "addData", expectedExceptions = DaoException.class)
    public void removeTestNegative(CustomBook book) throws DaoException {
        bookListDao.remove(book);
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
    public void findByIdTest(CustomBook expected) {
        CustomBook actual = bookListDao
                .findById("4a5f2a66-2035-4b28-ba3a-bf08fcda0ebe").get();
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestNegative() {
        Optional<CustomBook> actual = bookListDao
                .findById("4a5f2a66-1111-4b28-ba3a-bf08fcda0ebe");
        Optional<CustomBook> expected = Optional.empty();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void findByTitleTest(CustomBook book) {
        List<CustomBook> actual = bookListDao.findByTitle("Evgeniy Onegin");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTitleTestNegative() {
        List<CustomBook> actual = bookListDao.findByTitle("Mermaid");
        List<CustomBook> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void findByAuthorTest(CustomBook book) {
        Author author = new Author("Alexander", "Pushkin");
        List<CustomBook> actual = bookListDao.findByAuthor(author);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByAuthorTestNegative() {
        Author author = new Author("Alexander", "Petrov");
        List<CustomBook> actual = bookListDao.findByAuthor(author);
        List<CustomBook> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void findByNumberPagesTest(CustomBook book) {
        List<CustomBook> actual = bookListDao.findByNumberPages(250);
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByNumberPagesTestNegative() {
        List<CustomBook> actual = bookListDao.findByNumberPages(251);
        List<CustomBook> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void findByTypographyTest(CustomBook book) {
        List<CustomBook> actual = bookListDao.findByTypography("Moscow");
        List<CustomBook> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTypographyTestNegative() {
        List<CustomBook> actual = bookListDao.findByTypography("Minsk");
        List<CustomBook> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortById", dataProviderClass = SortDataProvider.class)
    public void testSortBooksById(List<CustomBook> expected) {
        List<CustomBook> actual = bookListDao.sortBooksById();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByTitle(List<CustomBook> expected) {
        List<CustomBook> actual = bookListDao.sortBooksByTitle();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByAuthor(List<CustomBook> expected) {
        List<CustomBook> actual = bookListDao.sortBooksByAuthor();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByNumberPages",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByPages(List<CustomBook> expected) {
        List<CustomBook> actual = bookListDao.sortBooksByPages();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTypography",
            dataProviderClass = SortDataProvider.class)
    public void testSortBooksByTypography(List<CustomBook> expected) {
        List<CustomBook> actual = bookListDao.sortBooksByTypography();
        assertEquals(actual, expected);
    }
}