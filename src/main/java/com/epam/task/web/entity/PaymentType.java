package com.epam.task.web.entity;

import java.util.Optional;

public enum PaymentType {
    CASH("CASH"),
    CARD("CARD"),
    PERSONAL_ACCOUNT("PERSONAL_ACCOUNT");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<PaymentType> fromString(String text) {
        for (PaymentType type : PaymentType.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
