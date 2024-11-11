package com.sqlite.demo.model.sud.converter;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            return LocalDateTime.parse(dbData).toLocalDate();
        }
        return null;
    }
}
