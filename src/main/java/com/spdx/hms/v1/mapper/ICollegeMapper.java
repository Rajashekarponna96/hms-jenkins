package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeCourseResponse;
import com.spdx.hms.v1.model.inbound.response.CollegePaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CollegeResponse;
import com.spdx.hms.v1.service.dto.request.CollegeRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCourseResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeMapperImpl")
public interface ICollegeMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "active", ignore = true)
    })
    CollegeRequestDto map(CollegeRequest requestDto);

    CollegeUpdateRequestDto map(CollegeUpdateRequest requestDto);

    CollegeResponse map(CollegeResponseDto responseDto);

    CollegePaginationResponse map(CollegePaginationResponseDto responseDto);

    Collection<CollegeCourseResponse> mapCollegeCourses(Collection<CollegeCourseResponseDto> collegeCourse);

    CollegeCourseResponse map(CollegeCourseResponseDto collegeCourseResponseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }


}
