package com.spdx.hms.v1.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CourseGetRequest;
import com.spdx.hms.v1.model.inbound.request.CourseSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CourseUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CourseResponse;
import com.spdx.hms.v1.model.inbound.response.StudentCounsellorResponse;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentCounsellorMapperImpl")
public interface IStudentCounsellorMapper extends IOptionalMapper {
	@Mappings({
        @Mapping(target = "active", ignore = true)
})

	  StudentCounsellorSaveRequestDto map(StudentCounsellorSaveRequest requestDto);
	
	  StudentCounsellorUpdateRequestDto map( StudentCounsellorUpdateRequest requestDto);
	  
	  StudentCounsellorGetRequestDto map(StudentCounsellorGetRequest requestDto);
	  
	  StudentCounsellorResponse map(StudentCounsellorResponseDto responsedto);
}
