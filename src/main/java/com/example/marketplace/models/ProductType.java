package com.example.marketplace.models;

public enum ProductType {
    ELECTRONICS("Электроника"),
    CLOTHING("Одежда"),
    BOOKS("Книги"),
    OTHER("Другое");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ProductType fromString(String text) {
        for (ProductType t : values()) {
            if (t.name().equalsIgnoreCase(text)) {
                return t;
            }
        }
        return ELECTRONICS; // или throw new IllegalArgumentException()
    }
}
