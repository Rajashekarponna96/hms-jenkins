package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.mapper.ICourseDomainMapper;
import com.spdx.hms.repository.ICourseRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICourseService;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private ICourseDomainMapper courseDomainMapper;

    @Override
    public CourseResponseDto save(CourseSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::checkCourseAlreadySaved)
                    .map(courseDomainMapper::map)
                    .map(courseRepository::save)
                    .map(courseDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occurred while saving course",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CourseResponseDto update(CourseUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CourseUpdateRequestDto::getCourseId)
                    .flatMap(courseRepository::findById)
                    .map(courseEntity -> courseDomainMapper.map(courseEntity,requestDto))
                    .map(courseRepository::save)
                    .map(courseDomainMapper::map)
                    .orElseGet(CourseResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CourseResponseDto retrieve(CourseGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CourseGetRequestDto::getCourseId)
                    .flatMap(courseRepository::findById)
                    .map(courseDomainMapper::map)
                    .orElseGet(CourseResponseDto::new);
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private CourseSaveRequestDto checkCourseAlreadySaved(
            CourseSaveRequestDto courseSaveRequestDto) {
        String courseName = courseSaveRequestDto.getCourseName().orElseThrow(
                () -> new HMSException(ErrorCode.HMS0010, ErrorCode.HMS0010.getMessage()));
        boolean courseExists = courseRepository.existsByCourseName(courseName);
        if (courseExists) {
            throw new HMSException(ErrorCode.HMS0011, ErrorCode.HMS0011.getMessage());
        }


        return courseSaveRequestDto;
    }

	@Override
	public List<CourseResponseDto> retrieve() {
		 try {
	            return 	courseRepository.findAll()
	            		.stream()
	                    .map(courseDomainMapper::map)
	                    .collect(Collectors.toList())
	                    ;
	                   // .orElseGet(CourseResponseDto::new);
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	        }
	}
}
