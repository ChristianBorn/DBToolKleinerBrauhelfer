package com.sqlite.demo.model.gebinde;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "status"}))
@Data
@AllArgsConstructor
public class Gebinde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private Integer anzahl;

    @Column(columnDefinition = "real default 0 not null")
    private Float fassungsvermoegen;

    @Column
    private String status;

    public Gebinde(String name, Integer anzahl, Float fassungsvermoegen, String status) {
        this.name = name;
        this.anzahl = anzahl;
        this.fassungsvermoegen = fassungsvermoegen;
        this.status = status;
    }

    public Gebinde() {
    }

    public Gebinde gebindeWithStatus(String status) {
        return new Gebinde(this.getId(),
                this.getName(),
                this.getAnzahl(),
                this.getFassungsvermoegen(),
                status);
    }

    public Gebinde gebindeWithAnzahl(int anzahl) {
        return new Gebinde(this.getId(),
                this.getName(),
                anzahl,
                this.getFassungsvermoegen(),
                this.getStatus());
    }
}
