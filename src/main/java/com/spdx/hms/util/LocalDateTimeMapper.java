package com.spdx.hms.util;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Mapper
public class LocalDateTimeMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public String asString(LocalDateTime date) {
        return date != null ? formatter.format(date) : null;
    }

    public LocalDateTime asDate(String date) {
        return date != null ? LocalDateTime.parse(date, formatter) : null;
    }
}
