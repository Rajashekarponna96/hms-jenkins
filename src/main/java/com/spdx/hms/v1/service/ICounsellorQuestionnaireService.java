package com.spdx.hms.v1.service;

import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnairePaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnairePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireResponseDto;

public interface ICounsellorQuestionnaireService {
    CounsellorQuestionnaireResponseDto save(CounsellorQuestionnaireSaveRequestDto requestDto);

    CounsellorQuestionnaireResponseDto update(CounsellorQuestionnaireUpdateRequestDto requestDto);

    CounsellorQuestionnaireResponseDto retrieve(CounsellorQuestionnaireGetRequestDto requestDto);

    CounsellorQuestionnairePaginationResponseDto retrieveAll(CounsellorQuestionnairePaginationRequestDto requestDto);

    Boolean delete(Long id);
}
