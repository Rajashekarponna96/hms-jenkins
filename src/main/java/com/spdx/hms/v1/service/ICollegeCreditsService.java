package com.spdx.hms.v1.service;

import com.spdx.hms.v1.service.dto.request.CollegeCreditsPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsResponseDto;

import java.util.List;

public interface ICollegeCreditsService {
	
	CollegeCreditsResponseDto save(CollegeCreditsSaveRequestDto requestDto);

	CollegeCreditsResponseDto update(CollegeCreditsUpdateRequestDto requestDto);
	
	CollegeCreditsResponseDto delete(Long id);
	
	CollegeCreditsResponseDto get(Long id);

	CollegeCreditsPaginationResponseDto retrieveAll(CollegeCreditsPaginationRequestDto requestDto);
}
