package com.spdx.hms.mapper;

import com.spdx.hms.entity.CollegeCreditsEntity;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeCreditsDomainMapperImpl")
public interface ICollegeCreditsDomainMapper extends IOptionalMapper {
    CollegeCreditsEntity map(CollegeCreditsSaveRequestDto requestDto);
    @AfterMapping
    default void mapAuditFields(@MappingTarget CollegeCreditsEntity CollegeCreditsEntity, CollegeCreditsSaveRequestDto requestDto) {
        CollegeCreditsEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        CollegeCreditsEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        //requestDto.getCreatedBy().ifPresent(CollegeCreditsEntity::setModifiedBy);
    }

    CollegeCreditsResponseDto map(CollegeCreditsEntity requestDto);

    default CollegeCreditsEntity map(CollegeCreditsEntity collegeCreditsEntity,
                                     CollegeCreditsUpdateRequestDto collegeCreditsUpdateRequestDto) {
        collegeCreditsUpdateRequestDto.getCreditPoints().ifPresent(collegeCreditsEntity::setCreditPoints);
        collegeCreditsUpdateRequestDto.getCollegeCode().ifPresent(collegeCreditsEntity::setCollegeCode);
        collegeCreditsEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        return collegeCreditsEntity;
    }

    default CollegeCreditsPaginationResponseDto mapPaginationResponse(Page<CollegeCreditsEntity> p) {
        CollegeCreditsPaginationResponseDto CollegeCreditsPaginationResponseDto = new CollegeCreditsPaginationResponseDto();
        CollegeCreditsPaginationResponseDto.setCurrentPage(p.getNumber());
        CollegeCreditsPaginationResponseDto.setTotalItems(p.getTotalElements());
        CollegeCreditsPaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<CollegeCreditsResponseDto> CollegeCreditsResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        CollegeCreditsPaginationResponseDto.setCollegeCredits(CollegeCreditsResponses);
        return CollegeCreditsPaginationResponseDto;
    }

}
