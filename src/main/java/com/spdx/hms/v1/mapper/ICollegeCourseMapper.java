package com.spdx.hms.v1.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeCourseRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeCourseResponse;
import com.spdx.hms.v1.service.dto.request.CollegeCourseRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCourseResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeCourseMapperImpl")
public interface ICollegeCourseMapper extends IOptionalMapper {

	CollegeCourseRequestDto map(CollegeCourseRequest requestDto);


    CollegeCourseResponse map(CollegeCourseResponseDto responseDto);
}
