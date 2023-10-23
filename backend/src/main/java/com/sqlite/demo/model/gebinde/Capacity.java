package com.sqlite.demo.model.gebinde;


import com.fasterxml.jackson.annotation.JsonProperty;

public interface Capacity {
    String getName();

    int getAnzahl();
    @JsonProperty("Summierte Kapazität")
    Float getSummierteKapazitaet();
}
