package com.spdx.hms.v1.service;

import java.util.List;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorFollowupResponseDto;



public interface IStudentCounsellorFollowupService {
	
	StudentCounsellorFollowupResponseDto save(StudentCounsellorFollowupSaveRequestDto requestDto);
	
	StudentCounsellorFollowupResponseDto update(StudentCounsellorFollowupUpdateRequestDto requestDto);
	
	StudentCounsellorFollowupResponseDto retrieve(StudentCounsellorFollowupGetRequestDto requestDto);
	
	List<StudentCounsellorFollowupResponseDto> retrieve();
	
	List<StudentCounsellorFollowupResponseDto> retriveData( Long stdCounsellorRequestId);
	
	Boolean delete(Long id);

}
