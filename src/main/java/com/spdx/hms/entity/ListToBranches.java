package com.spdx.hms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.Branches;
import com.spdx.hms.dto.FeeStructure;
import com.spdx.hms.dto.HostelFacilities;

public class ListToBranches implements AttributeConverter<List<Branches>, String> {
	private final static Gson GSON = new Gson();
   
	@Override
	public String convertToDatabaseColumn(List<Branches> attribute) {
		
		return attribute == null ? null : GSON.toJson(attribute);
	}

	@Override
	public List<Branches> convertToEntityAttribute(String dbData) {
		
		return dbData == null ? new ArrayList<>() : GSON.fromJson(dbData, new TypeToken<List<Branches>>(){}.getType());

	}

}
