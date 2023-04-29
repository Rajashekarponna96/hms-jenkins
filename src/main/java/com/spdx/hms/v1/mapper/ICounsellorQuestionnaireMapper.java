package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireGetRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CounsellorQuestionnairePaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CounsellorQuestionnaireResponse;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnairePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICounsellorQuestionnaireMapperImpl")
public interface ICounsellorQuestionnaireMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "active", ignore = true)
    })
    CounsellorQuestionnaireSaveRequestDto map(CounsellorQuestionnaireSaveRequest requestDto);

    CounsellorQuestionnaireUpdateRequestDto map(CounsellorQuestionnaireUpdateRequest requestDto);

    CounsellorQuestionnaireGetRequestDto map(CounsellorQuestionnaireGetRequest requestDto);

    CounsellorQuestionnaireResponse map(CounsellorQuestionnaireResponseDto responseDto);

    Collection<CounsellorQuestionnaireResponse> mapCounsellorQuestionnaires(Collection<CounsellorQuestionnaireResponseDto> responseDtos);

    CounsellorQuestionnairePaginationResponse map(CounsellorQuestionnairePaginationResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
