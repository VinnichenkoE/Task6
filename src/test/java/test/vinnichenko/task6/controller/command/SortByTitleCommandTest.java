package test.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.impl.SortByTitleCommand;
import com.vinnichenko.task6.exception.DaoException;
import com.vinnichenko.task6.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.vinnichenko.task6.dataprovider.SortDataProvider;
import test.vinnichenko.task6.dataprovider.TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SortByTitleCommandTest {

    @BeforeClass
    public void fillInWarehouse() throws DaoException {
        TestData.fillIn();
    }

    @Test(dataProvider = "sortByTitle",
            dataProviderClass = SortDataProvider.class)
    public void executeTest(List<CustomBook> sortedList) {
        Map<String, String> parameters = new HashMap<>();
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.SUCCESS_STATUS);
        expected.put(ResponseParameters.RESULT, sortedList.toString());
        Command command = new SortByTitleCommand();
        Map<String, String> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}