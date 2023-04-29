package com.spdx.hms.v1.mapper;

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
import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsGetRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeStudentFollowUpsResponse;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentFollowUpsResponseDto;
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICollegeStudentFollowUpsMapperImpl")
public interface ICollegeStudentFollowUpsMapper extends IOptionalMapper{
	@Mappings({
		@Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)		
})
	CollegeStudentFollowUpsSaveRequestDto map(CollegeStudentFollowUpsSaveRequest requestDto);
	@Mappings({
        @Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})
	
	CollegeStudentFollowUpsUpdateRequestDto map(CollegeStudentFollowUpsUpdateRequest requestDto);
	
	CollegeStudentFollowUpsGetRequestDto map(CollegeStudentFollowUpsGetRequest requestDto);
	
	CollegeStudentFollowUpsResponse map(CollegeStudentFollowUpsResponseDto responseDto);
	
	default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
	
	default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
