package com.epam.task.web.entity;

import java.util.Optional;

public enum OrderStatus {
    ACCEPTED("ACCEPTED"),
    PROCESSED("PROCESSED"),
    DONE("DONE");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<OrderStatus> fromString(String text) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.value.equalsIgnoreCase(text)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
