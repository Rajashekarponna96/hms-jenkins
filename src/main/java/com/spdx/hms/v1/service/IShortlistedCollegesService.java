package com.spdx.hms.v1.service;

import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesResponseDto;

import java.util.List;

public interface IShortlistedCollegesService {
	
	ShortlistedCollegesResponseDto save(ShortlistedCollegesSaveRequestDto requestDto);

	ShortlistedCollegesResponseDto update(ShortlistedCollegesUpdateRequestDto requestDto);
	
	ShortlistedCollegesResponseDto delete(Long id);
	
	List<ShortlistedCollegesResponseDto> get();

	ShortlistedCollegesPaginationResponseDto retrieveAll(ShortlistedCollegesPaginationRequestDto requestDto);
}
