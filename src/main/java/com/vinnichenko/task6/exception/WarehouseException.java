package com.vinnichenko.task6.exception;

public class WarehouseException extends Exception {
    public WarehouseException() {
    }

    public WarehouseException(String message) {
        super(message);
    }

    public WarehouseException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarehouseException(Throwable cause) {
        super(cause);
    }
}
