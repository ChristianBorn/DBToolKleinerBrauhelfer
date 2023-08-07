package com.sqlite.demo.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GebindeStatus {
    LEER("Leer"),
    VOLL("Voll");
    private final String displayName;

    GebindeStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}

