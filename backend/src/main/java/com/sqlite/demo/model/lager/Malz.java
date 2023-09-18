package com.sqlite.demo.model.lager;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Malz extends Lagerbestand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name = super.getName();
    private float menge = super.getMenge();
    private float preis = super.getPreis();
}
