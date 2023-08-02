package com.sqlite.demo.model.ausruestung;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ausruestung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "integer default 0")
    private int typ;

    @Column(columnDefinition = "real default 60")
    private float sudhausausbeute;
}
