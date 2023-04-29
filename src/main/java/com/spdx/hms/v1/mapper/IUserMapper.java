package com.spdx.hms.v1.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.UserRequest;
import com.spdx.hms.v1.model.inbound.request.UserUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.UserResponse;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.UserRequestDto;
import com.spdx.hms.v1.service.dto.request.UserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.UserResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IUserMapperImpl")
public interface IUserMapper extends IOptionalMapper {
	
	UserRequestDto map(UserRequest userRequest);
	
	UserResponse map(UserResponseDto userRequest);
	
	UserUpdateRequestDto map( UserUpdateRequest requestDto);
	

}
