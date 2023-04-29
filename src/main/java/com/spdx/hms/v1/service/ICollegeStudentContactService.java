package com.spdx.hms.v1.service;
import java.util.List;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentContactResponseDto;

public interface ICollegeStudentContactService {
	
	CollegeStudentContactResponseDto save(CollegeStudentContactSaveRequestDto requestDto);
	
	CollegeStudentContactResponseDto update(CollegeStudentContactUpdateRequestDto requestDto);
	
	CollegeStudentContactResponseDto retrieve(CollegeStudentContactGetRequestDto requestDto);
    
    List<CollegeStudentContactResponseDto> retrieve( );
    
    Boolean delete(Long id);

}
