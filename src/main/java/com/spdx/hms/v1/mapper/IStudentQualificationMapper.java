package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IDefaultMapper;
import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.util.LocalDateTimeMapper;
import com.spdx.hms.v1.model.inbound.request.StudentQualificationGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentQualificationSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentQualificationUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentQualificationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.dto.request.StudentQualificationGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentQualificationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentQualificationMapperImpl")
public interface IStudentQualificationMapper extends IOptionalMapper, IDefaultMapper {

    @Mappings({@Mapping(target = "active", ignore = true)})
    StudentQualificationSaveRequestDto map(StudentQualificationSaveRequest requestDto);

    StudentQualificationUpdateRequestDto map(StudentQualificationUpdateRequest requestDto);

    StudentQualificationGetRequestDto map(StudentQualificationGetRequest requestDto);

    StudentQualificationResponse map(StudentQualificationResponseDto responseDto);

    @Mapping(target = "studentExperiences", ignore = true)
    StudentResponse map(StudentResponseDto responseDto);

    default LocalDateTime mapTimeStamp(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    default LocalDateTime mapData(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
