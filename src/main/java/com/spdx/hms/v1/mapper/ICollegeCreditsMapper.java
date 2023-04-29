package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeCreditsGetRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeCreditsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeCreditsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeCreditsPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CollegeCreditsResponse;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeCreditsMapperImpl")
public interface ICollegeCreditsMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "active", ignore = true)
    })
    CollegeCreditsSaveRequestDto map(CollegeCreditsSaveRequest requestDto);

    CollegeCreditsUpdateRequestDto map(CollegeCreditsUpdateRequest requestDto);

    CollegeCreditsGetRequestDto map(CollegeCreditsGetRequest requestDto);

    CollegeCreditsResponse map(CollegeCreditsResponseDto responseDto);

    Collection<CollegeCreditsResponse> mapCollegeCredits(Collection<CollegeCreditsResponseDto> responseDtos);

    CollegeCreditsPaginationResponse map(CollegeCreditsPaginationResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
