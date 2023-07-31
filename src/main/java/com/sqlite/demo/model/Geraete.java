package com.sqlite.demo.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Geraete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "bezeichnung")
    private String bezeichnung;
//    @Column(name = "ausruestunganlagenid")
    @ManyToOne(targetEntity = Ausruestung.class)
    @JoinColumn(name = "ausruestunganlagenid", referencedColumnName="id", nullable=false)
    private Ausruestung ausruestungAnlagenId;
}