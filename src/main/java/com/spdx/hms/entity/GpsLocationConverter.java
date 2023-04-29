package com.spdx.hms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.FeeStructure;

public class GpsLocationConverter implements AttributeConverter<GpsLocation, String> {
	private final static Gson GSON = new Gson();
    @Override
    public String convertToDatabaseColumn(GpsLocation attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public GpsLocation convertToEntityAttribute(String dbData) {
        return dbData == null ? new GpsLocation() : GSON.fromJson(dbData, new TypeToken<GpsLocation>(){}.getType());
    }

}
