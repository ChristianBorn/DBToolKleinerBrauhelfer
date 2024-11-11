package com.sqlite.demo.model.sud;

import java.util.Arrays;
import java.util.Optional;

public enum Status {
    REZEPT(0, "Rezept"),
    GEBRAUT(1, "Gebraut"),
    ABGEFUELLT(2, "Abgefüllt"),
    VERBRAUCHT(3, "Verbraucht"),
    UNBEKANNT(99, "Unbekannt");


    private final int dbValue;
    private final String displayName;
    Status(int dbValue, String displayName) {
        this.dbValue = dbValue;
        this.displayName = displayName;
    }

    private int getDbValue() {
        return this.dbValue;
    }

    private String getDisplayName() {
        return this.displayName;
    }

    public static Status getStatusByInteger(Integer dbValue) {
        Optional<Status> status = Arrays.stream(Status.values())
                .filter(val -> val.getDbValue() == dbValue).findFirst();
        return status.orElse(UNBEKANNT);
    }
    public static String getStatusDisplayName(Integer dbValue) {
        Status status = getStatusByInteger(dbValue);
        return status.getDisplayName();
    }
}
