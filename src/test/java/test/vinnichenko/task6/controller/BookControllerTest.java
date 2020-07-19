package test.vinnichenko.task6.controller;

import com.vinnichenko.task6.controller.BookController;
import com.vinnichenko.task6.controller.RequestParameters;
import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task6.dataprovider.SortDataProvider;
import test.vinnichenko.task6.dataprovider.TestData;

import static org.testng.Assert.assertEquals;

import java.util.*;

public class BookControllerTest {

    BookController bookController;

    @BeforeClass
    public void fillInWarehouse() throws DaoException {
        TestData.fillIn();
    }

    @BeforeMethod
    public void setUp() {
        bookController = BookController.getInstance();
    }

    @Test(priority = 1)
    public void executeTaskTestAdd() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_TITLE, "War and Peace");
        request.put(RequestParameters.PARAMETER_AUTHORS, "Lev Tolstoy");
        request.put(RequestParameters.PARAMETER_NUMBER_PAGES, "581");
        request.put(RequestParameters.PARAMETER_TYPOGRAPHY, "Konica Minolta");
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "add_book");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "book")
    public Object[][] createDataBook() {
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lev", "Tolstoy");
        authors.add(author);
        CustomBook book =
                new CustomBook("4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe",
                        "War and Peace", authors, 581,
                        "Konica Minolta");
        return new Object[][]{{book}};
    }

    @Test(dataProvider = "book")
    public void executeTaskTestFindByAuthor(CustomBook book) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_AUTHORS, "Lev Tolstoy");
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "find_by_author");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        expected.put(ResponseParameters.RESULT, books.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void executeTaskTestFindById(CustomBook book) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.ID,
                "4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe");
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "find_by_id");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, book.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void executeTaskTestFindByTitle(CustomBook book) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_TITLE, "War and Peace");
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "find_by_title");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        expected.put(ResponseParameters.RESULT, books.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void executeTaskTestFindByNumberPages(CustomBook book) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_NUMBER_PAGES, "581");
        request.put(RequestParameters.PARAMETER_COMMAND_NAME,
                "find_by_number_pages");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        expected.put(ResponseParameters.RESULT, books.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "book")
    public void executeTaskTestFindByTypography(CustomBook book) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_TYPOGRAPHY, "Konica Minolta");
        request.put(RequestParameters.PARAMETER_COMMAND_NAME,
                "find_by_typography");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        expected.put(ResponseParameters.RESULT, books.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortById", dataProviderClass = SortDataProvider.class)
    public void executeTaskTestSortById(List<CustomBook> sortedList) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "sort_by_id");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, sortedList.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void executeTaskTestSortByTitle(List<CustomBook> sortedList) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "sort_by_title");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, sortedList.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByAuthor",
            dataProviderClass = SortDataProvider.class)
    public void executeTaskTestSortByAuthor(List<CustomBook> sortedList) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "sort_by_author");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, sortedList.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByNumberPages",
            dataProviderClass = SortDataProvider.class)
    public void executeTaskTestSortByNumberPages(List<CustomBook> sortedList) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME,
                "sort_by_number_pages");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, sortedList.toString());
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sortByTypography",
            dataProviderClass = SortDataProvider.class)
    public void executeTaskTestSortByTypography(List<CustomBook> sortedList) {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME,
                "sort_by_typography");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, sortedList.toString());
        assertEquals(actual, expected);
    }

    @Test
    public void executeTaskTestWrongCommand() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME,
                "something_wrong");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS, ResponseParameters.FAIL_STATUS);
        expected.put(ResponseParameters.MESSAGE,
                ResponseParameters.FAIL_MESSAGE);
        assertEquals(actual, expected);
    }

    @Test(priority = 2)
    public void executeTaskTestRemove() {
        Map<String, String> request = new HashMap<>();
        request.put(RequestParameters.PARAMETER_COMMAND_NAME, "remove_book");
        request.put(RequestParameters.ID,
                "4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe");
        Map<String, String> actual = bookController.executeTask(request);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        assertEquals(actual, expected);
    }
}