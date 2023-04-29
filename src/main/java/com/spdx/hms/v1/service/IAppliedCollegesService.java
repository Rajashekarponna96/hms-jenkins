package com.spdx.hms.v1.service;

import com.spdx.hms.v1.service.dto.request.AppliedCollegesPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesResponseDto;

import java.util.List;

public interface IAppliedCollegesService {
	
	AppliedCollegesResponseDto save(AppliedCollegesSaveRequestDto requestDto);

	AppliedCollegesResponseDto update(AppliedCollegesUpdateRequestDto requestDto);
	
//	AppliedCollegesResponseDto delete(Long id);
	
	List<AppliedCollegesResponseDto> get();

	AppliedCollegesPaginationResponseDto retrieveAll(AppliedCollegesPaginationRequestDto requestDto);
	
	Boolean delete(Long id);
}
