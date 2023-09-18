package com.sqlite.demo.model.sud;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String sudname;
    @Column(name = "erg_abgefuelltebiermenge", columnDefinition = "real default 0")
    private Float abgefuellteMenge;
}
