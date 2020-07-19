package test.vinnichenko.task6.util.parser;

import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.util.parser.AuthorParser;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class AuthorParserTest {

    @Test
    public void stringToAuthorTest() {
        AuthorParser authorParser = new AuthorParser();
        Author author = new Author("Alexander", "Pushkin");
        Author author2 = new Author("Lev", "Tolstoy");
        Set<Author> expected = new LinkedHashSet<>();
        expected.add(author);
        expected.add(author2);
        Set<Author> actual = authorParser
                .stringToAuthor("Alexander Pushkin Lev Tolstoy");
        assertEquals(actual, expected);
    }
}