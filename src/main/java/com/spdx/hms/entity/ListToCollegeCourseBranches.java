package com.spdx.hms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.Branches;
import com.spdx.hms.dto.CollegeCourseBranches;

public class ListToCollegeCourseBranches  implements AttributeConverter<List<CollegeCourseBranches>, String>{
	private final static Gson GSON = new Gson();
	@Override
	public String convertToDatabaseColumn(List<CollegeCourseBranches> attribute) {
		
		return attribute == null ? null : GSON.toJson(attribute);
	}

	@Override
	public List<CollegeCourseBranches> convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return dbData == null ? new ArrayList<>() : GSON.fromJson(dbData, new TypeToken<List<CollegeCourseBranches>>(){}.getType());

	}

}
