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
import com.spdx.hms.v1.model.inbound.request.StudentReportingInfoGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentReportingInfoSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentReportingInfoUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.StudentSaveRequest;
import com.spdx.hms.v1.model.inbound.response.StudentReportingInfoResponse;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentReportingInfoResponseDto;



@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentReportingInfoMapperImpl")
public interface IStudentReportingInfoMapper extends IOptionalMapper {
	
	@Mappings({
        @Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})
	StudentReportingInfoSaveRequestDto map(StudentReportingInfoSaveRequest requestDto);

    @Mappings({
            @Mapping(target = "createdTimestamp", ignore = true),
            @Mapping(target = "modifiedTimestamp", ignore = true),
            //@Mapping(target = "active", ignore = true)
    })
	
   
	//StudentReportingInfoSaveRequestDto map(StudentReportingInfoSaveRequest requestDto);

    StudentReportingInfoUpdateRequestDto map(StudentReportingInfoUpdateRequest requestDto);

    StudentReportingInfoGetRequestDto map(StudentReportingInfoGetRequest requestDto);
    
    StudentReportingInfoResponse map(StudentReportingInfoResponseDto responseDto);

	
    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
	

}
