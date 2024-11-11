package com.sqlite.demo.model.sud.converter;

import com.sqlite.demo.model.sud.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<String, Integer> {
    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        return Status.getStatusDisplayName(dbData);
    }
}
