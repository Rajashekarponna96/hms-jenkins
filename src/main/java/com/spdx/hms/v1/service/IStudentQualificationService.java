package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentQualificationGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentQualificationResponseDto;

public interface IStudentQualificationService {
    StudentQualificationResponseDto save(StudentQualificationSaveRequestDto requestDto);

    StudentQualificationResponseDto update(StudentQualificationUpdateRequestDto requestDto);

    StudentQualificationResponseDto retrieve(StudentQualificationGetRequestDto requestDto);
    
    List<StudentQualificationResponseDto> retrieve(Long profileId);

    Boolean delete(Long id);
}
