package com.vinnichenko.task6.validator;

public class BookValidator {

    private static final int MAX_LENGTH = 200;
    private static final int MAX_PAGES = 1000;
    private static final String REGEX_UUID = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    public boolean isTitleValid(String title) {
        int length = title.length();
        boolean result = false;
        if (length > 0 && length < MAX_LENGTH) {
            result = true;
        }
        return result;
    }

    public boolean isNumberPagesValid(int numberPages) {
        boolean result = false;
        if (numberPages > 0 && numberPages < MAX_PAGES) {
            result = true;
        }
        return result;
    }

    public boolean isIdValid(String id) {
        return id.matches(REGEX_UUID);
    }
}
