package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentExperienceGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentExperienceResponseDto;

public interface IStudentExperienceService {
    StudentExperienceResponseDto save(StudentExperienceSaveRequestDto requestDto);

    StudentExperienceResponseDto update(StudentExperienceUpdateRequestDto requestDto);

    StudentExperienceResponseDto retrieve(StudentExperienceGetRequestDto requestDto);
    
    List<StudentExperienceResponseDto> retrieve(Long studentId);

    Boolean delete(Long id);
}
