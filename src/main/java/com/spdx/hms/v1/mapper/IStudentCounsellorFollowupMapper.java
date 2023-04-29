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

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorFollowupGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorFollowupSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorFollowupUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentCounsellorFollowupResponse;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorFollowupResponseDto;
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentCounsellorFollowupMapperImpl")
public interface IStudentCounsellorFollowupMapper extends IOptionalMapper {
	@Mappings({
	     @Mapping(target = "active", ignore = true)		
})
	//StudentCounsellorFollowupSaveRequestDto map(StudentCounsellorFollowupSaveRequest requestDto);
	
	StudentCounsellorFollowupUpdateRequestDto map(StudentCounsellorFollowupUpdateRequest requestDto);
	StudentCounsellorFollowupGetRequestDto map(StudentCounsellorFollowupGetRequest requestDto);
	StudentCounsellorFollowupResponse map(StudentCounsellorFollowupResponseDto requestDto);
	StudentCounsellorFollowupSaveRequestDto map(StudentCounsellorFollowupSaveRequest requestDto);
	

}
