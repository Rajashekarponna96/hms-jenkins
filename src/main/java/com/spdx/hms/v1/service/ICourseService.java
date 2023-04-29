package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;

public interface ICourseService {
    CourseResponseDto save(CourseSaveRequestDto requestDto);

    CourseResponseDto update(CourseUpdateRequestDto requestDto);

    CourseResponseDto retrieve(CourseGetRequestDto requestDto);
    
    List<CourseResponseDto> retrieve();
}
