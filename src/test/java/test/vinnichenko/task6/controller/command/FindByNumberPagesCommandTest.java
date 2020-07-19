package test.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.RequestParameters;
import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.impl.FindByNumberPagesCommand;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task6.dataprovider.TestData;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class FindByNumberPagesCommandTest {

    @BeforeClass
    public void fillInWarehouse() throws DaoException {
        TestData.fillIn();
    }

    @DataProvider(name = "executeData")
    public Object[][] createDataExecute() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameters.PARAMETER_NUMBER_PAGES, "581");
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lev", "Tolstoy");
        authors.add(author);
        CustomBook book =
                new CustomBook("4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe",
                        "War and Peace", authors, 581,
                        "Konica Minolta");
        List<CustomBook> books = new ArrayList<>();
        books.add(book);
        Map<String, String> response = new HashMap<>();
        response.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        response.put(ResponseParameters.RESULT, books.toString());
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put(RequestParameters.PARAMETER_NUMBER_PAGES, "-15");
        Map<String, String> response2 = new HashMap<>();
        response2.put(ResponseParameters.STATUS, ResponseParameters.FAIL_STATUS);
        response2.put(ResponseParameters.MESSAGE, "invalid value of parameter");
        return new Object[][]{
                {parameters, response},
                {parameters2, response2}
        };
    }

    @Test(dataProvider = "executeData")
    public void executeTest(Map<String, String> parameters,
                            Map<String, String> expected) {
        Command command = new FindByNumberPagesCommand();
        Map<String, String> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}