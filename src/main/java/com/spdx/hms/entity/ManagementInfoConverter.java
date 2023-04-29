package com.spdx.hms.entity;


import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.spdx.hms.dto.ManagementInfo;

public class ManagementInfoConverter implements AttributeConverter<ManagementInfo, String> {
	private final static Gson GSON = new Gson(); 
	@Override
    public String convertToDatabaseColumn(ManagementInfo attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public ManagementInfo convertToEntityAttribute(String dbData) {
        return dbData == null ? new ManagementInfo() : GSON.fromJson(dbData, new TypeToken<ManagementInfo>(){}.getType());
    }

}
