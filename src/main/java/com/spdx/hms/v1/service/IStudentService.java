package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;

public interface IStudentService {
    StudentResponseDto save(StudentSaveRequestDto requestDto);

    StudentResponseDto update(StudentUpdateRequestDto requestDto);

    StudentResponseDto retrieve(StudentGetRequestDto requestDto);

    StudentPaginationResponseDto retrieveAll(StudentPaginationRequestDto requestDto);

    Boolean delete(Long id);
    
    List<StudentResponseDto> retrieveList(List<Long> ids);
}
