package com.vinnichenko.task6.parser;

import com.vinnichenko.task6.model.entity.Author;
import com.vinnichenko.task6.validator.AuthorValidator;

import java.util.LinkedHashSet;
import java.util.Set;

public class AuthorParser {

    private static final String SEPARATOR = "\\s+";

    public Set<Author> stringToAuthor(String stringAuthor) {
        String[] authors = stringAuthor.split(SEPARATOR);
        Set<Author> authorsSet = new LinkedHashSet<>();
        for (int i = 0; i < authors.length; i += 2) {
            String firstName = authors[i];
            String lastName = authors[i + 1];
            AuthorValidator authorValidator = new AuthorValidator();
            if (authorValidator.isWord(firstName) && authorValidator.isWord(lastName)) {
                Author author = new Author(firstName, lastName);
                authorsSet.add(author);
            }
        }
        return authorsSet;
    }
}