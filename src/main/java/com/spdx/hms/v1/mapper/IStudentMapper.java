package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.StudentExperienceSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentExperienceResponse;
import com.spdx.hms.v1.model.inbound.response.StudentPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.dto.request.StudentExperienceSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentExperienceResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentMapperImpl")
public interface IStudentMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "createdTimestamp", ignore = true),
            @Mapping(target = "modifiedTimestamp", ignore = true),
            @Mapping(target = "active", ignore = true)
    })
    StudentSaveRequestDto map(StudentSaveRequest requestDto);

    @Mappings({
            @Mapping(target = "createdTimestamp", ignore = true),
            @Mapping(target = "modifiedTimestamp", ignore = true),
            @Mapping(target = "active", ignore = true)
    })
    StudentExperienceSaveRequestDto map(StudentExperienceSaveRequest requestDto);

    StudentUpdateRequestDto map(StudentUpdateRequest requestDto);

    StudentGetRequestDto map(StudentGetRequest requestDto);

    StudentResponse map(StudentResponseDto responseDto);

    StudentExperienceResponse map(StudentExperienceResponseDto responseDto);

    Collection<StudentExperienceResponse> mapExperiences(Collection<StudentExperienceResponseDto> responseDto);

    Collection<StudentResponse> mapStudents(Collection<StudentResponseDto> responseDtos);

    StudentPaginationResponse map(StudentPaginationResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
