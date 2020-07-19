package test.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.impl.AddBookCommand;
import com.vinnichenko.task6.controller.RequestParameters;
import com.vinnichenko.task6.controller.ResponseParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class AddBookCommandTest {

    @DataProvider(name = "executeData")
    public Object[][] createDataExecute() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameters.PARAMETER_TITLE, "War and Peace");
        parameters.put(RequestParameters.PARAMETER_AUTHORS, "Lev Tolstoy");
        parameters.put(RequestParameters.PARAMETER_NUMBER_PAGES, "581");
        parameters.put(RequestParameters.PARAMETER_TYPOGRAPHY, "Moscow");
        Map<java.lang.String, java.lang.String> response = new HashMap<>();
        response.put(ResponseParameters.STATUS, ResponseParameters.SUCCESS_STATUS);
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put(RequestParameters.PARAMETER_TITLE, "War and Peace");
        parameters2.put(RequestParameters.PARAMETER_AUTHORS, "Lev Tolstoy Lev");
        parameters2.put(RequestParameters.PARAMETER_NUMBER_PAGES, "581");
        parameters2.put(RequestParameters.PARAMETER_TYPOGRAPHY, "Moscow");
        Map<java.lang.String, java.lang.String> response2 = new HashMap<>();
        response2.put(ResponseParameters.STATUS, ResponseParameters.FAIL_STATUS);
        response2.put(ResponseParameters.MESSAGE, "incorrect parameters");
        return new Object[][]{
                {parameters, response},
                {parameters2, response2}
        };
    }

    @Test(dataProvider = "executeData")
    public void executeTest(Map<String, String> parameters,
                            Map<String, String> expected) {
        Command command = new AddBookCommand();
        Map<String, String> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}