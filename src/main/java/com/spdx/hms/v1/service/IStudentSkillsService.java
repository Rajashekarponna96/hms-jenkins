package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentSkillsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentSkillsResponseDto;

public interface IStudentSkillsService {
    StudentSkillsResponseDto save(StudentSkillsSaveRequestDto requestDto);

    StudentSkillsResponseDto update(StudentSkillsUpdateRequestDto requestDto);

    StudentSkillsResponseDto retrieve(StudentSkillsGetRequestDto requestDto);
    
    List<StudentSkillsResponseDto> retrieveByProfileId(Long profileId);

    Boolean delete(Long id);
}
