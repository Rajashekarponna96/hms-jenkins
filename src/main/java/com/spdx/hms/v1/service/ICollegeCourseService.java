package com.spdx.hms.v1.service;


import com.spdx.hms.v1.service.dto.request.CollegeCourseRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCourseResponseDto;

import java.util.List;

public interface ICollegeCourseService {

    CollegeCourseResponseDto save(CollegeCourseRequestDto requestDto);

    CollegeCourseResponseDto update(CollegeCourseRequestDto requestDto);

    Boolean delete(Long id);

    CollegeCourseResponseDto retrieve(Long id);

    List<CollegeCourseResponseDto> retrieveByCollee(Long id);

    // StudentResponseDto retrieve(StudentGetRequestDto requestDto);
}
