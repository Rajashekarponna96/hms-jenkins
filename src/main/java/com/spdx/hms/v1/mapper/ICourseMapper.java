package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CourseGetRequest;
import com.spdx.hms.v1.model.inbound.request.CourseSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CourseUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CourseResponse;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICourseMapperImpl")
public interface ICourseMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "active", ignore = true)
    })
    CourseSaveRequestDto map(CourseSaveRequest requestDto);

    CourseUpdateRequestDto map(CourseUpdateRequest requestDto);

    CourseGetRequestDto map(CourseGetRequest requestDto);

    CourseResponse map(CourseResponseDto responseDto);
}
