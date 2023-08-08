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
    private Float fassungsvermoegen;

    @Column
    private GebindeStatus status;

    public Gebinde(String name, Integer anzahl, Float fassungsvermoegen, GebindeStatus status) {
        this.name = name;
        this.anzahl = anzahl;
        this.fassungsvermoegen = fassungsvermoegen;
        this.status = status;
    }

    public Gebinde() {}
}
