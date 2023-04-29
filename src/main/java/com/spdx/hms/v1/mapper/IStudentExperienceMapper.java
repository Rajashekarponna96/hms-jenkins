package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.util.LocalDateTimeMapper;
import com.spdx.hms.v1.model.inbound.request.StudentExperienceGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentExperienceSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentExperienceUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentExperienceResponse;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.dto.request.StudentExperienceGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentExperienceResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentExperienceMapperImpl")
public interface IStudentExperienceMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "createdTimestamp", ignore = true),
            @Mapping(target = "modifiedTimestamp", ignore = true),
            @Mapping(target = "active", ignore = true)
    })
    StudentExperienceSaveRequestDto map(StudentExperienceSaveRequest requestDto);
    @Mappings({
//        @Mapping(target = "createdTimestamp", ignore = true),
//        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})

    StudentExperienceUpdateRequestDto map(StudentExperienceUpdateRequest requestDto);

    StudentExperienceGetRequestDto map(StudentExperienceGetRequest requestDto);

    StudentExperienceResponse map(StudentExperienceResponseDto responseDto);

    @Mapping(target = "studentExperiences", ignore = true)
    StudentResponse map(StudentResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
