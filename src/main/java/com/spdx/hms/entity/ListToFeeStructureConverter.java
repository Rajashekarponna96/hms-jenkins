package com.spdx.hms.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.FeeStructure;

public class ListToFeeStructureConverter implements AttributeConverter<List<FeeStructure>, String> {
	private final static Gson GSON = new Gson();
    @Override
    public String convertToDatabaseColumn(List<FeeStructure> attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public List<FeeStructure> convertToEntityAttribute(String dbData) {
        return dbData == null ? new ArrayList<>() : GSON.fromJson(dbData, new TypeToken<List<FeeStructure>>(){}.getType());
    }
}
