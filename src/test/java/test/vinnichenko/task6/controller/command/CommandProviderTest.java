package test.vinnichenko.task6.controller.command;

import com.vinnichenko.task6.controller.command.Command;
import com.vinnichenko.task6.controller.command.CommandProvider;
import com.vinnichenko.task6.controller.command.impl.AddBookCommand;
import com.vinnichenko.task6.controller.command.impl.FindByTitleCommand;
import com.vinnichenko.task6.controller.command.impl.SortByIdCommand;
import com.vinnichenko.task6.controller.command.impl.WrongCommand;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {

    @DataProvider(name = "getCommandData")
    public Object[][] createDataGetCommand() {
        return new Object[][]{
                {"add_book", new AddBookCommand()},
                {"find_by_title", new FindByTitleCommand()},
                {"some_command", new WrongCommand()},
                {"sort_by_id", new SortByIdCommand()}
        };
    }

    @Test(dataProvider = "getCommandData")
    public void getCommandTest(String commandName, Command expected) {
        CommandProvider commandProvider = new CommandProvider();
        Command actual = commandProvider.getCommand(commandName);
        assertEquals(actual.getClass().getSimpleName(), expected.getClass().getSimpleName());
    }
}