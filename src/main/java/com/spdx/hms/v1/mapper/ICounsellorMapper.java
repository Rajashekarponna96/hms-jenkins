package com.spdx.hms.v1.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CounsellorGetRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CounsellorResponse;
import com.spdx.hms.v1.model.inbound.response.StudentCounsellorResponse;
import com.spdx.hms.v1.service.dto.request.CounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICounsellorMapperImpl")
public interface ICounsellorMapper extends IOptionalMapper {
	
	@Mappings({
        @Mapping(target = "active", ignore = true)
})
	CounsellorSaveRequestDto map(CounsellorSaveRequest requestDto);
	
	CounsellorUpdateRequestDto map(CounsellorUpdateRequest requestDto);
	  
	CounsellorGetRequestDto map(CounsellorGetRequest requestDto);
	  
	CounsellorResponse map(CounsellorResponseDto responsedto);


}
