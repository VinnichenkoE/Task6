package com.vinnichenko.task6.util.parser;

import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.validator.DataValidator;

import java.util.LinkedHashSet;
import java.util.Set;

public class AuthorParser {

    private static final String SEPARATOR = "\\s";

    public Set<Author> stringToAuthor(String stringAuthor) {
        DataValidator dataValidator = new DataValidator();
        Set<Author> authorsSet = new LinkedHashSet<>();
        if (dataValidator.isAuthorValid(stringAuthor)) {
            String[] authors = stringAuthor.trim().split(SEPARATOR);
            for (int i = 0; i < authors.length; i += 2) {
                String firstName = authors[i];
                String lastName = authors[i + 1];
                Author author = new Author(firstName, lastName);
                authorsSet.add(author);
            }
        }
        return authorsSet;
    }
}