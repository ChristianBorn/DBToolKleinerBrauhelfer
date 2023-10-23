package com.sqlite.demo.model.ausruestung;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Teil von Ausrüstung")
    private Ausruestung ausruestungAnlagenId;
}