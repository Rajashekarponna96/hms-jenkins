package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.CollegeUserGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeUserResponseDto;


public interface ICollegeUserService {
	
    CollegeUserResponseDto save(CollegeUserSaveRequestDto requestDto);
	
	CollegeUserResponseDto update(CollegeUserUpdateRequestDto requestDto);
	
	CollegeUserResponseDto retrieve(CollegeUserGetRequestDto requestDto);
    
    List<CollegeUserResponseDto> retrieve( );
    
    Boolean delete(Long id);

}
