package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.ShortlistedCollegesGetRequest;
import com.spdx.hms.v1.model.inbound.request.ShortlistedCollegesSaveRequest;
import com.spdx.hms.v1.model.inbound.request.ShortlistedCollegesUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.ShortlistedCollegesPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.ShortlistedCollegesResponse;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesGetRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IShortlistedCollegesMapperImpl")
public interface IShortlistedCollegesMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "active", ignore = true)
    })
    ShortlistedCollegesSaveRequestDto map(ShortlistedCollegesSaveRequest requestDto);

    ShortlistedCollegesUpdateRequestDto map(ShortlistedCollegesUpdateRequest requestDto);

    ShortlistedCollegesGetRequestDto map(ShortlistedCollegesGetRequest requestDto);

    ShortlistedCollegesResponse map(ShortlistedCollegesResponseDto responseDto);

    Collection<ShortlistedCollegesResponse> mapShortlistedColleges(Collection<ShortlistedCollegesResponseDto> responseDtos);

    ShortlistedCollegesPaginationResponse map(ShortlistedCollegesPaginationResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
