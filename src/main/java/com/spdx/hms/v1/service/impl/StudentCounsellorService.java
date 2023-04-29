package com.spdx.hms.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.LangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.StudentCounsellorEntity;
import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.mapper.IStudentCounsellorDomainMapper;
import com.spdx.hms.repository.IStudentCounsellorRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentCounsellorService;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CourseUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.response.CourseResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class StudentCounsellorService implements IStudentCounsellorService {

	@Autowired
	private IStudentCounsellorRepository studentCounsellorRepository;
	
	@Autowired
	private IStudentCounsellorDomainMapper  studentCounsellorDomainMapper;
	

	@Override
	public StudentCounsellorResponseDto save(StudentCounsellorSaveRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)

                    .map(studentCounsellorDomainMapper::map)
                    .map(studentCounsellorRepository::save)
                    .map(studentCounsellorDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occurred while saving course",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
		
	}

	@Override
	public StudentCounsellorResponseDto update(StudentCounsellorUpdateRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentCounsellorUpdateRequestDto::getStdCounsellorRequestId)
                    .flatMap(studentCounsellorRepository::findById)
                    .map(StudentCounsellorEntity ->studentCounsellorDomainMapper.map(StudentCounsellorEntity,requestDto))
                    .map(studentCounsellorRepository::save)
                    .map(studentCounsellorDomainMapper::map)
                    .orElseGet(StudentCounsellorResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}
	

	@Override
	public StudentCounsellorResponseDto retrieve(StudentCounsellorGetRequestDto requestDto) {

		try {
	            return Optional.ofNullable(requestDto)
	                    .map(this::fetchByCriteria)
	                    .map(studentCounsellorDomainMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	        }
	}

	@Override
	public List<StudentCounsellorResponseDto> retrieve() {
		try {
            return 	studentCounsellorRepository.findAll()
            		.stream()
                    .map(studentCounsellorDomainMapper::map)
                    .collect(Collectors.toList())
                    ;
                   // .orElseGet(CourseResponseDto::new);
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
}
	
	@Override
	public Boolean delete(Long id) {
		
		 try {
	            return Optional.ofNullable(id)
	                    .map(studentCounsellorRepository::deactivateStudentCounsellor)
	                    .map(d -> d > 0)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	        }
		
	}
	
	private StudentCounsellorEntity fetchByCriteria(StudentCounsellorGetRequestDto requestDto) {
		if(requestDto.getStdCounsellorRequestId().filter(stdCounsellorRequestId -> stdCounsellorRequestId > 0).isPresent()) {
				return studentCounsellorRepository.findById(requestDto.getStdCounsellorRequestId().orElse(0L))
						.orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
	}
	//			else if (requestDto.getStudentId().filter(studentId -> studentId > 0).isPresent()) {
//            return requestDto.getStudentId()
//                    .map(studentCounsellorRepository::findByStudentId)
//                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
//        }else if (requestDto.getCounsellorId().filter(counsellorId -> counsellorId >0).isPresent()) {
//        	return requestDto.getCounsellorId()
//        			.map(studentCounsellorRepository::findByCounsellorId)
//        			.orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
//        }
//	
		throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
		
	}

	@Override
	public List<StudentCounsellorResponseDto> retriveData(Long studentId) {
		List<StudentCounsellorResponseDto> response=new ArrayList<StudentCounsellorResponseDto>();
		return studentCounsellorRepository.findByStudentId(studentId)
				.stream()
				.map(studentCounsellorDomainMapper::map)
	            .collect(Collectors.toList()) ;
	}

	@Override
	public List<StudentCounsellorResponseDto> retrieveDataList(Long counsellorId) {
		List<StudentCounsellorResponseDto> response=new ArrayList<StudentCounsellorResponseDto>();
		return studentCounsellorRepository.findByCounsellorId(counsellorId)
				.stream()
				.map(studentCounsellorDomainMapper::map)
	            .collect(Collectors.toList()) ;
		
	}
       
}
