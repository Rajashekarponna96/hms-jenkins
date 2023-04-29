package com.spdx.hms.v1.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentReportingInfoEntity;
import com.spdx.hms.entity.UserEntity;
import com.spdx.hms.mapper.IStudentReportingInfoDomainMapper;
import com.spdx.hms.repository.IStudentReportingInfoRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentReportingInfoService;
import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentReportingInfoResponseDto;
import com.spdx.hms.v1.service.dto.response.UniversityResponseDto;
import com.spdx.hms.v1.service.dto.response.UserResponseDto;

import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentReportingInfoService implements IStudentReportingInfoService {
	
	@Autowired
	private IStudentReportingInfoRepository studentReportingInfoRepository;
	
	@Autowired
	private IStudentReportingInfoDomainMapper studentReportingInfoDomainMapper;

	@Override
	public StudentReportingInfoResponseDto save(StudentReportingInfoSaveRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .map(studentReportingInfoDomainMapper::map)
                    .flatMap(this::validate)
                    .map(studentReportingInfoRepository::save)
                    .map(studentReportingInfoDomainMapper::map)
                    .orElseGet(StudentReportingInfoResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0028, ErrorCode.HMS0028.getMessage());
        }					
		
	}


	@Override
	public StudentReportingInfoResponseDto retrieve(StudentReportingInfoGetRequestDto requestDto) {
		
		 try {
	            return Optional.ofNullable(requestDto)
	                    .map(this::fetchByCriteria)
	                    .map(studentReportingInfoDomainMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0023, ErrorCode.HMS0023.getMessage());
	        }
	}

	   private StudentReportingInfoEntity fetchByCriteria(StudentReportingInfoGetRequestDto requestDto) {
	        if (requestDto.getStdAdmissionId().filter(id -> id > 0).isPresent()) {
	            return studentReportingInfoRepository.findById(requestDto.getStdAdmissionId().orElse(0L))
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0024, ErrorCode.HMS0024.getMessage()));
	        } 
	        
	        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
	    }

	
	
	@Override
	public List<StudentReportingInfoResponseDto> retrieve() {
		try {
            return 	studentReportingInfoRepository.findAll()
            		.stream()
                    .map(studentReportingInfoDomainMapper::map)
                    .collect(Collectors.toList());
                    
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}


	@Override
	public StudentReportingInfoResponseDto update(StudentReportingInfoUpdateRequestDto requestDto) {
		/*try {
			return Optional.ofNullable(requestDto)
					.map(studentReportingInfoDomainMapper::map)
					.flatMap(this::generate)
					.map(studentReportingInfoRepository::save)
					.map(studentReportingInfoDomainMapper::map)
					.orElseGet(StudentReportingInfoResponseDto::new);
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}*/
		
		try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentReportingInfoUpdateRequestDto::getStdAdmissionId)
                    .flatMap(studentReportingInfoRepository::findById)
                    .map(studentReportingInfoEntity -> studentReportingInfoDomainMapper.map(studentReportingInfoEntity, requestDto))
                    .map(studentReportingInfoRepository::save)
                    .map(studentReportingInfoDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}
/*	private Optional<StudentReportingInfoEntity> generate(StudentReportingInfoEntity student) {
		
		return Optional.ofNullable(student);
		
	}*/
	
	@Override
	public Boolean delete(Long id) {
		try {
            return Optional.ofNullable(id)
                    .map(studentReportingInfoRepository::deactivateStudent)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
		
	}
	
	private Optional<StudentReportingInfoEntity> validate(StudentReportingInfoEntity studentEntity) {
		 StudentReportingInfoEntity studentId = studentReportingInfoRepository.findByStudentId(
				 studentEntity.getStdAdmissionId());
	        if (studentId != null) {
	            throw new HMSException(ErrorCode.HMS0028, ErrorCode.HMS0028.getMessage());
	        } else {
	            return Optional.of(studentEntity);
	        }
	    }
	
	
}
