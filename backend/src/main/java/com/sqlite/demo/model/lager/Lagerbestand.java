package com.sqlite.demo.model.lager;

import lombok.Data;

import javax.persistence.Column;

@Data
public abstract class Lagerbestand {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "integer default 0")
    private float menge;

    @Column(columnDefinition = "real default 0")
    private float preis;
}
