package com.vinnichenko.task6.parser;

import com.vinnichenko.task6.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorParser {
    public List<Author> stringToAuthor(String stringAuthor) {
        String[] authors = stringAuthor.split("\\s");

        List<Author> authorList = new ArrayList<>();
        for (int i = 0; i < authors.length; i +=2) {
            String firstName = authors[i];
            String lastName = authors[i+1];
            Author author = new Author(firstName, lastName);
            authorList.add(author);
        }
        return authorList;
    }
}
