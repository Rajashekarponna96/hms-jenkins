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
import com.spdx.hms.v1.model.inbound.request.CollegeUserGetRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeUserSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeUserUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeUserResponse;
import com.spdx.hms.v1.service.dto.request.CollegeUserGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeUserResponseDto;
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICollegeUserMapperImpl")
public interface ICollegeUserMapper extends IOptionalMapper{
	@Mappings({
		@Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})
	CollegeUserSaveRequestDto map(CollegeUserSaveRequest requestDto);
	@Mappings({
        @Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})
	CollegeUserUpdateRequestDto map(CollegeUserUpdateRequest requestDto);
	CollegeUserGetRequestDto map(CollegeUserGetRequest requestDto);
	CollegeUserResponse map(CollegeUserResponseDto responseDto);
	
	default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
