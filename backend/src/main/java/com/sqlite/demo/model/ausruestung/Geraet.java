package com.sqlite.demo.model.ausruestung;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "geraete")
@Data
public class Geraet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "bezeichnung")
    private String bezeichnung;

    @ManyToOne(targetEntity = Ausruestung.class)
    @JoinColumn(name = "ausruestunganlagenid", referencedColumnName = "id", nullable = false)
    private Ausruestung ausruestungAnlagenId;
}