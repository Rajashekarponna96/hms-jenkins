package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.AppliedCollegesGetRequest;
import com.spdx.hms.v1.model.inbound.request.AppliedCollegesSaveRequest;
import com.spdx.hms.v1.model.inbound.request.AppliedCollegesUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.AppliedCollegesPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.AppliedCollegesResponse;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesGetRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IAppliedCollegesMapperImpl")
public interface IAppliedCollegesMapper extends IOptionalMapper {
    boolean Inprogress = true;
	boolean CollegeReviewingApplication = true;

	@Mappings({
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "status", ignore = Inprogress),
            @Mapping(target = "comment", ignore = CollegeReviewingApplication)
            
    })
    AppliedCollegesSaveRequestDto map(AppliedCollegesSaveRequest requestDto);

    AppliedCollegesUpdateRequestDto map(AppliedCollegesUpdateRequest requestDto);

    AppliedCollegesGetRequestDto map(AppliedCollegesGetRequest requestDto);

    AppliedCollegesResponse map(AppliedCollegesResponseDto responseDto);

    Collection<AppliedCollegesResponse> mapAppliedColleges(Collection<AppliedCollegesResponseDto> responseDtos);

    AppliedCollegesPaginationResponse map(AppliedCollegesPaginationResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
