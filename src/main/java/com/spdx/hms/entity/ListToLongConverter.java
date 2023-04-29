package com.spdx.hms.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ListToLongConverter implements AttributeConverter<List<Long>, String> {
	private final static Gson GSON = new Gson();
    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        return dbData == null ? Collections.emptyList() : GSON.fromJson(dbData,new TypeToken<List<Long>>(){}.getType());
    }
}
