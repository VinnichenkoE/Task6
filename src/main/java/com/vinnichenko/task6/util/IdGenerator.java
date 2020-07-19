package com.vinnichenko.task6.util;

import java.util.UUID;

public class IdGenerator {

    private IdGenerator() {
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
