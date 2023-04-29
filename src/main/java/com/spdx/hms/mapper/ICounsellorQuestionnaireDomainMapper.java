package com.spdx.hms.mapper;

import com.spdx.hms.entity.CounsellorQuestionnaireEntity;
import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnairePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICounsellorQuestionnaireDomainMapperImpl")
public interface ICounsellorQuestionnaireDomainMapper extends IOptionalMapper {
    CounsellorQuestionnaireEntity map(CounsellorQuestionnaireSaveRequestDto requestDto);

    @AfterMapping
    default void mapAuditFields(
            @MappingTarget CounsellorQuestionnaireEntity counsellorQuestionnaireEntity,
            CounsellorQuestionnaireSaveRequestDto requestDto) {
        counsellorQuestionnaireEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        counsellorQuestionnaireEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        requestDto.getCreatedBy().ifPresent(counsellorQuestionnaireEntity::setModifiedBy);
    }

    CounsellorQuestionnaireResponseDto map(CounsellorQuestionnaireEntity requestDto);

    default CounsellorQuestionnaireEntity map(
            CounsellorQuestionnaireEntity counsellorQuestionnaireEntity,
            CounsellorQuestionnaireUpdateRequestDto counsellorQuestionnaireUpdateRequestDto) {

		counsellorQuestionnaireUpdateRequestDto.getModifiedBy().ifPresent(counsellorQuestionnaireEntity::setModifiedBy);
		counsellorQuestionnaireUpdateRequestDto.getIndustryType()
				.ifPresent(counsellorQuestionnaireEntity::setIndustryType);
		counsellorQuestionnaireUpdateRequestDto.getModifiedTimestamp()
				.ifPresent(counsellorQuestionnaireEntity::setModifiedTimestamp);
		counsellorQuestionnaireUpdateRequestDto.getActive().ifPresent(counsellorQuestionnaireEntity::setActive);
		counsellorQuestionnaireUpdateRequestDto.getQuestionnaire()
				.ifPresent(counsellorQuestionnaireEntity::setQuestionnaire);
		counsellorQuestionnaireUpdateRequestDto.getModifiedBy().ifPresent(counsellorQuestionnaireEntity::setModifiedBy);
        
        counsellorQuestionnaireEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        return counsellorQuestionnaireEntity;
    }

    default CounsellorQuestionnairePaginationResponseDto mapPaginationResponse(Page<CounsellorQuestionnaireEntity> p) {
        CounsellorQuestionnairePaginationResponseDto counsellorQuestionnairePaginationResponseDto =
                new CounsellorQuestionnairePaginationResponseDto();
        counsellorQuestionnairePaginationResponseDto.setCurrentPage(p.getNumber());
        counsellorQuestionnairePaginationResponseDto.setTotalItems(p.getTotalElements());
        counsellorQuestionnairePaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<CounsellorQuestionnaireResponseDto> counsellorQuestionnaireResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        counsellorQuestionnairePaginationResponseDto.setCounsellorQuestionnaires(counsellorQuestionnaireResponses);
        return counsellorQuestionnairePaginationResponseDto;
    }

}
