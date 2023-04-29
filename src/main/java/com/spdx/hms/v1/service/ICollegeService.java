package com.spdx.hms.v1.service;

import com.spdx.hms.v1.model.inbound.response.CollegeResponse;
import com.spdx.hms.v1.service.dto.request.CollegePaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeResponseDto;

import java.util.List;

public interface ICollegeService {
	
	CollegeResponseDto save(CollegeRequestDto requestDto);

	CollegeResponseDto update(CollegeUpdateRequestDto requestDto);
	
	CollegeResponseDto delete(Long id);
	
	CollegeResponseDto retrieve(Long id);

	CollegePaginationResponseDto retrieveAll(CollegePaginationRequestDto requestDto);

	List<CollegeResponseDto> retrieveList(List<Long> ids);
}
