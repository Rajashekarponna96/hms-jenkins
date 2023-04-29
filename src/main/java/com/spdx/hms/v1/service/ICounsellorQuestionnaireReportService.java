package com.spdx.hms.v1.service;

import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportResponseDto;

public interface ICounsellorQuestionnaireReportService {
    CounsellorQuestionnaireReportResponseDto save(CounsellorQuestionnaireReportSaveRequestDto requestDto);

    CounsellorQuestionnaireReportResponseDto update(CounsellorQuestionnaireReportUpdateRequestDto requestDto);

    CounsellorQuestionnaireReportResponseDto retrieve(CounsellorQuestionnaireReportGetRequestDto requestDto);

    CounsellorQuestionnaireReportPaginationResponseDto retrieveAll(CounsellorQuestionnaireReportPaginationRequestDto requestDto);

    Boolean delete(Long id);
}
