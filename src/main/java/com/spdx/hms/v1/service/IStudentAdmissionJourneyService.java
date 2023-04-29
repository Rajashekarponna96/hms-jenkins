package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneySaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionJourneyResponseDto;


public interface IStudentAdmissionJourneyService {
	
	StudentAdmissionJourneyResponseDto save(StudentAdmissionJourneySaveRequestDto requestDto);

	
	StudentAdmissionJourneyResponseDto retrieve(StudentAdmissionJourneyGetRequestDto requestDto);
	
	List<StudentAdmissionJourneyResponseDto> retrieve();
	
	StudentAdmissionJourneyResponseDto update(StudentAdmissionJourneyUpdateRequestDto requestDto);	
	
	Boolean delete(Long id);


}
