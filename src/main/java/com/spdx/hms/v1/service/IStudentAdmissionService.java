package com.spdx.hms.v1.service;

import java.util.List;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionResponseDto;

public interface IStudentAdmissionService {

	StudentAdmissionResponseDto save(StudentAdmissionSaveRequestDto requestDto);

	StudentAdmissionResponseDto update(StudentAdmissionUpdateRequestDto requestDto);

	StudentAdmissionResponseDto retrieve(StudentAdmissionGetRequestDto requestDto);

	List<StudentAdmissionResponseDto> retrieve();

	Boolean delete(Long id);
	
	StudentAdmissionPaginationResponseDto retrieveAll(StudentAdmissionPaginationRequestDto requestDto);

}
