package com.sqlite.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Gebinde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private Integer anzahl;

    @Column(columnDefinition = "real default 0 not null")
    private Float fassungsvermögen;

    @Column
    private String status;
}
