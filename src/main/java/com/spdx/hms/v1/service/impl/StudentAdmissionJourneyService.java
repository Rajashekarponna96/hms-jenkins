package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.StudentAdmissionJourneyEntity;
import com.spdx.hms.entity.StudentReportingInfoEntity;
import com.spdx.hms.mapper.IStudentAdmissionJourneyDomainMapper;
import com.spdx.hms.repository.IStudentAdmissionJourneyRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentAdmissionJourneyService;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneySaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionJourneyResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentReportingInfoResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentAdmissionJourneyService implements IStudentAdmissionJourneyService {
	
	
	@Autowired
    private IStudentAdmissionJourneyRepository stdAdminJourneyRepository;
    @Autowired
    private IStudentAdmissionJourneyDomainMapper stdAdminJourneyDomainMapper;
	@Override
	public StudentAdmissionJourneyResponseDto save(StudentAdmissionJourneySaveRequestDto requestDto) {
		try {
			  return Optional.ofNullable(requestDto)
	                    .map(stdAdminJourneyDomainMapper::map)
	                    .flatMap(this::validate)
	                    .map(stdAdminJourneyRepository::save)
	                    .map(stdAdminJourneyDomainMapper::map)
	                    .orElseGet(StudentAdmissionJourneyResponseDto::new);
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0028, ErrorCode.HMS0028.getMessage());
	        }			
		
	}
	
	
	@Override
	public StudentAdmissionJourneyResponseDto retrieve(StudentAdmissionJourneyGetRequestDto requestDto) {
		 try {
	            return Optional.ofNullable(requestDto)
	                    .map(this::fetchByCriteria)
	                    .map(stdAdminJourneyDomainMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0023, ErrorCode.HMS0023.getMessage());
	        }
	}

	   private StudentAdmissionJourneyEntity fetchByCriteria(StudentAdmissionJourneyGetRequestDto requestDto) {
	        if (requestDto.getStdProfileId().filter(id -> id > 0).isPresent()) {
	            return stdAdminJourneyRepository.findById(requestDto.getStdProfileId().orElse(0L))
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0024, ErrorCode.HMS0024.getMessage()));
	        } 
	        
	        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
	    }
	@Override
	public List<StudentAdmissionJourneyResponseDto> retrieve() {
		try {
            return 	stdAdminJourneyRepository.findAll()
            		.stream()
                    .map(stdAdminJourneyDomainMapper::map)
                    .collect(Collectors.toList());
                    
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
                    .map(stdAdminJourneyRepository::deactivateStudent)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0004, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }

	}
	
	private Optional<StudentAdmissionJourneyEntity> validate(StudentAdmissionJourneyEntity studentEntity) {
		StudentAdmissionJourneyEntity studentId = stdAdminJourneyRepository.findByStudentId(
				 studentEntity.getStdProfileId());
	        if (studentId != null) {
	            throw new HMSException(ErrorCode.HMS0028, ErrorCode.HMS0028.getMessage());
	        } else {
	            return Optional.of(studentEntity);
	        }
	    }


	@Override
	public StudentAdmissionJourneyResponseDto update(StudentAdmissionJourneyUpdateRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentAdmissionJourneyUpdateRequestDto::getStdProfileId)
                    .flatMap(stdAdminJourneyRepository::findById)
                    .map(StudentAdmissionJourneyEntity -> stdAdminJourneyDomainMapper.map(StudentAdmissionJourneyEntity, requestDto))
                    .map(stdAdminJourneyRepository::save)
                    .map(stdAdminJourneyDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
		
	}
}


