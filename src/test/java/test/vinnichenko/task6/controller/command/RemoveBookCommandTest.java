package test.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.RequestParameters;
import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.impl.RemoveBookCommand;
import com.vinnichenko.task6.exception.DaoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.vinnichenko.task6.dataprovider.TestData;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class RemoveBookCommandTest {

    @BeforeClass
    public void fillInWarehouse() throws DaoException {
        TestData.fillIn();
    }

    @DataProvider(name = "executeData")
    public Object[][] createDataExecute() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(RequestParameters.ID,
                "4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe");
        Map<String, String> response = new HashMap<>();
        response.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put(RequestParameters.ID,
                "123g4567-e89b-12d3-a456-426655440000");
        Map<String, String> response2 = new HashMap<>();
        response2.put(ResponseParameters.STATUS, ResponseParameters.FAIL_STATUS);
        response2.put(ResponseParameters.MESSAGE, "incorrect value of id");
        return new Object[][]{
                {parameters, response},
                {parameters2, response2}
        };
    }

    @Test(dataProvider = "executeData")
    public void executeTest(Map<String, String> parameters,
                            Map<String, String> expected) {
        Command command = new RemoveBookCommand();
        Map<String, String> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}