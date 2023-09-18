package com.sqlite.demo.model.gebinde;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GebindeDTO {
    private String name;
    private Integer anzahl;
    private Float fassungsvermoegen;
    private GebindeStatus status;
}
