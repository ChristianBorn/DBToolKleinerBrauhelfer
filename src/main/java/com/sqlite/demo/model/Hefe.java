package com.sqlite.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Hefe extends Lagerbestand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name = super.getName();
    private int menge = super.getMenge();
    private float preis = super.getPreis();
}
