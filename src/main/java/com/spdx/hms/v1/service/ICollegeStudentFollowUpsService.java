package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentFollowUpsResponseDto;

public interface ICollegeStudentFollowUpsService {

	CollegeStudentFollowUpsResponseDto save(CollegeStudentFollowUpsSaveRequestDto requestDto);
	
	CollegeStudentFollowUpsResponseDto update(CollegeStudentFollowUpsUpdateRequestDto requestDto);
	
	CollegeStudentFollowUpsResponseDto retrieve(CollegeStudentFollowUpsGetRequestDto requestDto);
    
    List<CollegeStudentFollowUpsResponseDto> retrieve( );
    
    Boolean delete(Long id);
}
