package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentReportingInfoGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentReportingInfoResponseDto;



public interface IStudentReportingInfoService {
	
	
	StudentReportingInfoResponseDto save(StudentReportingInfoSaveRequestDto requestDto);
	
	StudentReportingInfoResponseDto update(StudentReportingInfoUpdateRequestDto requestDto);
	
	StudentReportingInfoResponseDto retrieve(StudentReportingInfoGetRequestDto requestDto);
	
	List<StudentReportingInfoResponseDto> retrieve();
	
	Boolean delete(Long id);
	
	
	
	
	

}
