package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.UniversityGetRequest;
import com.spdx.hms.v1.model.inbound.request.UniversitySaveRequest;
import com.spdx.hms.v1.model.inbound.request.UniversityUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.UniversityResponse;
import com.spdx.hms.v1.service.dto.request.UniversityGetRequestDto;
import com.spdx.hms.v1.service.dto.request.UniversitySaveRequestDto;
import com.spdx.hms.v1.service.dto.request.UniversityUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.UniversityResponseDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IUniversityMapperImpl")
public interface IUniversityMapper extends IOptionalMapper {
	@Mappings({
		@Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
		@Mapping(target = "active", ignore = true)
    })

    UniversitySaveRequestDto map(UniversitySaveRequest requestDto);
	@Mappings({
		@Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
		@Mapping(target = "active", ignore = true)
    })

    UniversityUpdateRequestDto map(UniversityUpdateRequest requestDto);

    UniversityGetRequestDto map(UniversityGetRequest requestDto);

    UniversityResponse map(UniversityResponseDto responseDto);
    
    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

}
