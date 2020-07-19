package test.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.ResponseParameters;
import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.impl.WrongCommand;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class WrongCommandTest {

    @Test
    public void executeTest() {
        Map<String, String> parameters = new HashMap<>();
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameters.STATUS,
                ResponseParameters.FAIL_STATUS);
        expected.put(ResponseParameters.MESSAGE,
                ResponseParameters.FAIL_MESSAGE);
        Command command = new WrongCommand();
        Map<String, String> actual = command.execute(parameters);
        assertEquals(actual, expected);
    }
}