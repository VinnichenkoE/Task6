package test.vinnichenko.task6.validator;

import com.vinnichenko.task6.validator.DataValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataValidatorTest {

    DataValidator dataValidator;

    @BeforeMethod
    public void setUp() {
        dataValidator = new DataValidator();
    }

    @DataProvider(name = "isNumberPagesValidData")
    public Object[][] createDataIsNumberPagesValid() {
        return new Object[][]{
                {"110", true},
                {"-15", false},
                {"1100", false},
                {"adsad", false}
        };
    }

    @Test(dataProvider = "isNumberPagesValidData")
    public void isNumberPagesValidTest(String numberPages, boolean expected) {
        boolean actual = dataValidator.isNumberPagesValid(numberPages);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isIdValidData")
    public Object[][] createDataIsIdValid() {
        return new Object[][]{
                {"4a5f2a66-2036-4b28-ba3a-bf08fcda0ebe", true},
                {"123g4567-e89b-12d3-a456-426655440000", false}
        };
    }

    @Test(dataProvider = "isIdValidData")
    public void isIdValidTest(String id, boolean expected) {
        boolean actual = dataValidator.isIdValid(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isDigitData")
    public Object[][] createDataIsDigit() {
        return new Object[][]{
                {"123", true},
                {"1a3", false}
        };
    }

    @Test(dataProvider = "isDigitData")
    public void isDigitTest(String number, boolean expected) {
        boolean actual = dataValidator.isDigit(number);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isAuthorValidData")
    public Object[][] createDataIsAuthorValid() {
        return new Object[][]{
                {"Lev Tolstoy Alexander Pushkin", true},
                {"Lev Tolstoy Alexander", false},
                {"L2d Tolstoy", false}
        };
    }

    @Test(dataProvider = "isAuthorValidData")
    public void isAuthorValidTest(String authors, boolean expected) {
        boolean actual = dataValidator.isAuthorValid(authors);
        assertEquals(actual, expected);
    }
}