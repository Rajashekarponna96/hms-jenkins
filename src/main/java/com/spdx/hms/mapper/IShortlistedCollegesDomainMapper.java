package com.spdx.hms.mapper;

import com.spdx.hms.entity.ShortlistedCollegesEntity;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IShortlistedCollegesDomainMapperImpl")
public interface IShortlistedCollegesDomainMapper extends IOptionalMapper {
    ShortlistedCollegesEntity map(ShortlistedCollegesSaveRequestDto requestDto);
    @AfterMapping
    default void mapAuditFields(@MappingTarget ShortlistedCollegesEntity ShortlistedCollegesEntity, ShortlistedCollegesSaveRequestDto requestDto) {
        ShortlistedCollegesEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        ShortlistedCollegesEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        //requestDto.getCreatedBy().ifPresent(ShortlistedCollegesEntity::setModifiedBy);
    }

    ShortlistedCollegesResponseDto map(ShortlistedCollegesEntity requestDto);

    default ShortlistedCollegesEntity map(ShortlistedCollegesEntity ShortlistedCollegesEntity, ShortlistedCollegesUpdateRequestDto ShortlistedCollegesUpdateRequestDto) {


        ShortlistedCollegesUpdateRequestDto.getModifiedBy()
                .ifPresent(ShortlistedCollegesEntity::setModifiedBy);
        ShortlistedCollegesEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        return ShortlistedCollegesEntity;
    }
    default ShortlistedCollegesPaginationResponseDto mapPaginationResponse(Page<ShortlistedCollegesEntity> p) {
        ShortlistedCollegesPaginationResponseDto ShortlistedCollegesPaginationResponseDto = new ShortlistedCollegesPaginationResponseDto();
        ShortlistedCollegesPaginationResponseDto.setCurrentPage(p.getNumber());
        ShortlistedCollegesPaginationResponseDto.setTotalItems(p.getTotalElements());
        ShortlistedCollegesPaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<ShortlistedCollegesResponseDto> shortlistedCollegesResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        ShortlistedCollegesPaginationResponseDto.setShortlistedColleges(shortlistedCollegesResponses);
        return ShortlistedCollegesPaginationResponseDto;
    }

}
