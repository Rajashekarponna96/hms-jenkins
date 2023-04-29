package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireReportGetRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireReportSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireReportUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CounsellorQuestionnaireReportPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CounsellorQuestionnaireReportResponse;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportResponseDto;
import org.mapstruct.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICounsellorQuestionnaireReportMapperImpl")
public interface ICounsellorQuestionnaireReportMapper extends IOptionalMapper {
    @Mappings({
            @Mapping(target = "active", ignore = true)
    })
    CounsellorQuestionnaireReportSaveRequestDto map(CounsellorQuestionnaireReportSaveRequest requestDto);

    CounsellorQuestionnaireReportUpdateRequestDto map(CounsellorQuestionnaireReportUpdateRequest requestDto);

    CounsellorQuestionnaireReportGetRequestDto map(CounsellorQuestionnaireReportGetRequest requestDto);

    CounsellorQuestionnaireReportResponse map(CounsellorQuestionnaireReportResponseDto responseDto);

    Collection<CounsellorQuestionnaireReportResponse> mapCounsellorQuestionnaireReports(Collection<CounsellorQuestionnaireReportResponseDto> responseDtos);

    CounsellorQuestionnaireReportPaginationResponse map(CounsellorQuestionnaireReportPaginationResponseDto responseDto);

    default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
