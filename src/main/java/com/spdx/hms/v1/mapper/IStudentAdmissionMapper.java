package com.spdx.hms.v1.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionSavetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.AppliedCollegesResponse;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionResponse;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentAdmissionMapperImpl")
public interface IStudentAdmissionMapper extends IOptionalMapper {
  boolean Inprogress = true;

    @Mappings({
            @Mapping(target = "createdTimestamp", ignore = true),
            @Mapping(target = "modifiedTimestamp", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "status", ignore = Inprogress),
    })
    StudentAdmissionSaveRequestDto map(StudentAdmissionSavetRequest requestDto);

	StudentAdmissionUpdateRequestDto map(StudentAdmissionUpdateRequest requestDto);

	StudentAdmissionGetRequestDto map(StudentAdmissionGetRequest requestDto);

	StudentAdmissionResponse map(StudentAdmissionResponseDto responseDto);
	
	Collection<StudentAdmissionResponse> mapStudentAdmission(Collection<StudentAdmissionResponseDto> responseDtos);


	StudentAdmissionPaginationResponse map(StudentAdmissionPaginationResponseDto responseDto);


    
    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
