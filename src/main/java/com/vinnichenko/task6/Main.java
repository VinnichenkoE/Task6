package com.vinnichenko.task6;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UUID id = UUID.randomUUID();
        String idStr  = id.toString();
        UUID id2 = UUID.fromString(idStr);
        System.out.println(id);
        System.out.println(id.equals(id2));
    }
}
