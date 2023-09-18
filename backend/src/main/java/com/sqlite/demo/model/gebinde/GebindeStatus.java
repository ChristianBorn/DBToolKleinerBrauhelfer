package com.sqlite.demo.model.gebinde;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GebindeStatus {
    LEER("leer"),
    VOLL("voll");
    private final String displayName;

    GebindeStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
