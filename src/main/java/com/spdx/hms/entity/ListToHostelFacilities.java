package com.spdx.hms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.FeeStructure;
import com.spdx.hms.dto.HostelFacilities;

public class ListToHostelFacilities implements AttributeConverter<List<HostelFacilities>, String>{
	private final static Gson GSON = new Gson();
    @Override
    public String convertToDatabaseColumn(List<HostelFacilities> attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public List<HostelFacilities> convertToEntityAttribute(String dbData) {
        return dbData == null ? new ArrayList<>() : GSON.fromJson(dbData, new TypeToken<List<HostelFacilities>>(){}.getType());
    }

}
