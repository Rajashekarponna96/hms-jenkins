package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.CounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorResponseDto;


public interface ICounsellorService {
	
	CounsellorResponseDto save(CounsellorSaveRequestDto requestDto);
	 
	CounsellorResponseDto update(CounsellorUpdateRequestDto requestDto);
	
	CounsellorResponseDto  retrieve(CounsellorGetRequestDto requestDto);
	
	List<CounsellorResponseDto> retrieve();
	
	Boolean delete(Long id);
}
