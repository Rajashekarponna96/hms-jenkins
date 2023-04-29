package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;


public interface IStudentCounsellorService {


	StudentCounsellorResponseDto save(StudentCounsellorSaveRequestDto requestDto);
	 
	StudentCounsellorResponseDto update(StudentCounsellorUpdateRequestDto requestDto);
	
	StudentCounsellorResponseDto  retrieve(StudentCounsellorGetRequestDto requestDto);
	
	List<StudentCounsellorResponseDto> retrieve();
	
	Boolean delete(Long id);
	
	List<StudentCounsellorResponseDto> retriveData(Long studentId);
	
	List<StudentCounsellorResponseDto> retrieveDataList(Long counsellorId);
	
	
}
