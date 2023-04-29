package com.spdx.hms.mapper;

import com.spdx.hms.entity.CounsellorQuestionnaireReportEntity;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICounsellorQuestionnaireReportDomainMapperImpl")
public interface ICounsellorQuestionnaireReportDomainMapper extends IOptionalMapper {
    CounsellorQuestionnaireReportEntity map(CounsellorQuestionnaireReportSaveRequestDto requestDto);

    @AfterMapping
    default void mapAuditFields(
            @MappingTarget CounsellorQuestionnaireReportEntity CounsellorQuestionnaireReportEntity,
            CounsellorQuestionnaireReportSaveRequestDto requestDto) {
        CounsellorQuestionnaireReportEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        CounsellorQuestionnaireReportEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        requestDto.getCreatedBy().ifPresent(CounsellorQuestionnaireReportEntity::setModifiedBy);
    }

    CounsellorQuestionnaireReportResponseDto map(CounsellorQuestionnaireReportEntity requestDto);

    default CounsellorQuestionnaireReportEntity map(
            CounsellorQuestionnaireReportEntity CounsellorQuestionnaireReportEntity,
            CounsellorQuestionnaireReportUpdateRequestDto CounsellorQuestionnaireReportUpdateRequestDto) {

		CounsellorQuestionnaireReportUpdateRequestDto.getModifiedBy()
				.ifPresent(CounsellorQuestionnaireReportEntity::setModifiedBy);
		CounsellorQuestionnaireReportUpdateRequestDto.getActive()
				.ifPresent(CounsellorQuestionnaireReportEntity::setActive);
		CounsellorQuestionnaireReportUpdateRequestDto.getIndustryType()
				.ifPresent(CounsellorQuestionnaireReportEntity::setIndustryType);
		CounsellorQuestionnaireReportUpdateRequestDto.getModifiedTimestamp()
				.ifPresent(CounsellorQuestionnaireReportEntity::setModifiedTimestamp);
		CounsellorQuestionnaireReportUpdateRequestDto.getQuestionnaire()
				.ifPresent(CounsellorQuestionnaireReportEntity::setQuestionnaire);
		   CounsellorQuestionnaireReportEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        return CounsellorQuestionnaireReportEntity;
    }

    default CounsellorQuestionnaireReportPaginationResponseDto mapPaginationResponse(Page<CounsellorQuestionnaireReportEntity> p) {
        CounsellorQuestionnaireReportPaginationResponseDto CounsellorQuestionnaireReportPaginationResponseDto =
                new CounsellorQuestionnaireReportPaginationResponseDto();
        CounsellorQuestionnaireReportPaginationResponseDto.setCurrentPage(p.getNumber());
        CounsellorQuestionnaireReportPaginationResponseDto.setTotalItems(p.getTotalElements());
        CounsellorQuestionnaireReportPaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<CounsellorQuestionnaireReportResponseDto> CounsellorQuestionnaireReportResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        CounsellorQuestionnaireReportPaginationResponseDto.setCounsellorQuestionnaireReports(
                CounsellorQuestionnaireReportResponses);
        return CounsellorQuestionnaireReportPaginationResponseDto;
    }

}
