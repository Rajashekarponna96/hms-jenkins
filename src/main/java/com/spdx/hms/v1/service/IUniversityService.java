package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.UniversityGetRequestDto;
import com.spdx.hms.v1.service.dto.request.UniversitySaveRequestDto;
import com.spdx.hms.v1.service.dto.request.UniversityUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.UniversityResponseDto;

public interface IUniversityService {
    UniversityResponseDto save(UniversitySaveRequestDto requestDto);

    UniversityResponseDto update(UniversityUpdateRequestDto requestDto);

    UniversityResponseDto retrieve(UniversityGetRequestDto requestDto);
    
    List<UniversityResponseDto> retrieve( );
    
    Boolean delete(Long id);
}
