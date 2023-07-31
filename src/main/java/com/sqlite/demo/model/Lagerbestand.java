package com.sqlite.demo.model;

import lombok.Data;

import javax.persistence.*;
@Data
public abstract class Lagerbestand {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "integer default 0")
    private int menge;

    @Column(columnDefinition = "real default 0")
    private float preis;
}
