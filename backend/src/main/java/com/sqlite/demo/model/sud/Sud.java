package com.sqlite.demo.model.sud;

import com.sqlite.demo.model.sud.converter.DateConverter;
import com.sqlite.demo.model.sud.converter.StatusConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Sud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String sudname;
    @Column
    private Long menge;
    @Column
    private String braudatum;
    @Column(name = "erg_abgefuelltebiermenge", columnDefinition = "real default 0")
    private Float abgefuellteMenge;
    @Column(name = "Status", columnDefinition = "real default 0")
    @Convert(converter = StatusConverter.class)
    private String status;
    @Column(name = "Abfuelldatum")
    @Convert(converter = DateConverter.class)
    private LocalDate abfuelldatum;
}
