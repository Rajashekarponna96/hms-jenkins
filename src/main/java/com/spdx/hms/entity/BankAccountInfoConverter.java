package com.spdx.hms.entity;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.BankAccountInfo;

public class BankAccountInfoConverter implements AttributeConverter<BankAccountInfo, String>{
	private final static Gson GSON = new Gson(); 
	@Override
    public String convertToDatabaseColumn(BankAccountInfo attribute) {
        return attribute == null ? null : GSON.toJson(attribute);
    }

    @Override
    public BankAccountInfo convertToEntityAttribute(String dbData) {
        return dbData == null ? new BankAccountInfo() : GSON.fromJson(dbData, new TypeToken<BankAccountInfo>(){}.getType());
    }
}
