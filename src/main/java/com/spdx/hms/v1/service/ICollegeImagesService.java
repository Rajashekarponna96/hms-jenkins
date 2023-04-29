package com.spdx.hms.v1.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spdx.hms.v1.service.dto.request.CollegeImagesRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeImagesResponseDto;

public interface ICollegeImagesService {
	
	List<CollegeImagesResponseDto> save(CollegeImagesRequestDto requestDto);
	
	List<CollegeImagesResponseDto> save(CollegeImagesRequestDto requestDto,List<MultipartFile> files);
	
	String saveFile(MultipartFile file);

	CollegeImagesResponseDto update(CollegeImagesRequestDto requestDto);
	
	CollegeImagesResponseDto delete(Long id);
	
	List<CollegeImagesResponseDto> get();

   // StudentResponseDto retrieve(StudentGetRequestDto requestDto);
}
