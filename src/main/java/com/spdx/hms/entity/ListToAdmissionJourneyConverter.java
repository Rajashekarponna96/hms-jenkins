package com.spdx.hms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.AdmissionJourney;

public class ListToAdmissionJourneyConverter implements AttributeConverter<List<AdmissionJourney>, String> {
	private final static Gson GSON = new Gson();
    @Override
    public String convertToDatabaseColumn(List<AdmissionJourney> attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public List<AdmissionJourney> convertToEntityAttribute(String dbData) {
        return dbData == null ? new ArrayList<>() : GSON.fromJson(dbData, new TypeToken<List<AdmissionJourney>>(){}.getType());
    }
}
