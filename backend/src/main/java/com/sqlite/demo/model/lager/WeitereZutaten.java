package com.sqlite.demo.model.lager;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "weiterezutaten")
@Data
public class WeitereZutaten extends Lagerbestand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "integer default 0")
    private Integer einheit;
    private String name = super.getName();
    private float menge = super.getMenge();
    private float preis = super.getPreis();
}
