package com.vinnichenko.task6.validator;

public class AuthorValidator {

    private static final String REGEX_WORD = "\\p{Alpha}";

    public boolean isWord(String input) {
        boolean result = false;
        if (input.matches(REGEX_WORD)) {
            result = true;
        }
        return result;
    }
}
