package com.spdx.hms.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.StudentCounsellorEntity;
import com.spdx.hms.entity.StudentCounsellorFollowupEntity;
import com.spdx.hms.mapper.IStudentCounsellorFollowupDomainMapper;
import com.spdx.hms.repository.IStudentCounsellorFollowupRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentCounsellorFollowupService;
import com.spdx.hms.v1.service.IStudentCounsellorService;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorFollowupResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentCounsellorFollowupService implements IStudentCounsellorFollowupService{
	@Autowired
	IStudentCounsellorFollowupRepository studentCounsellorFollowupRepository;
	
	@Autowired
	IStudentCounsellorFollowupDomainMapper studentCounsellorFollowupDomainMapper;

	@Override
	public StudentCounsellorFollowupResponseDto save(StudentCounsellorFollowupSaveRequestDto requestDto) {
		try {
			return Optional.ofNullable(requestDto)
//                    .map(this::checkStudentCounsellorFollowupAlreadySaved)
                    .map(studentCounsellorFollowupDomainMapper::map)
                    .map(studentCounsellorFollowupRepository::save)
                    .map(studentCounsellorFollowupDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occurred while saving course",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
		
		
	}

	@Override
	public StudentCounsellorFollowupResponseDto update(StudentCounsellorFollowupUpdateRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentCounsellorFollowupUpdateRequestDto::getCnlgReqId)
                    .flatMap(studentCounsellorFollowupRepository::findById)
                    .map(StudentCounsellorFollowupEntity ->studentCounsellorFollowupDomainMapper.map(StudentCounsellorFollowupEntity,requestDto))
                    .map(studentCounsellorFollowupRepository::save)
                    .map(studentCounsellorFollowupDomainMapper::map)
                    .orElseGet(StudentCounsellorFollowupResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public StudentCounsellorFollowupResponseDto retrieve(StudentCounsellorFollowupGetRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .map(this::fetchByCriteria)
                    .map(studentCounsellorFollowupDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public List<StudentCounsellorFollowupResponseDto> retrieve() {
		try {
            return 	studentCounsellorFollowupRepository.findAll()
            		.stream()
                    .map(studentCounsellorFollowupDomainMapper::map)
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
                    .map(studentCounsellorFollowupRepository::deactivateStudentCounsellorFollowup)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	
	}
	
//	private StudentCounsellorFollowupSaveRequestDto checkStudentCounsellorFollowupAlreadySaved(
//			StudentCounsellorFollowupSaveRequestDto studentCounsellorFollowupSaveRequestDto ) {
//		Long stdCounsellorRequestId=studentCounsellorFollowupSaveRequestDto.getStdCounsellorRequestId().orElseThrow(
//				() -> new HMSException(ErrorCode.HMS0010, ErrorCode.HMS0010.getMessage()));
//		boolean stdCounsellorRequestIdExits=studentCounsellorFollowupRepository.existsByStdCounsellorRequestId(stdCounsellorRequestId);
//		if(stdCounsellorRequestIdExits) {
//            throw new HMSException(ErrorCode.HMS0023, ErrorCode.HMS0023.getMessage());
//        }
//		return studentCounsellorFollowupSaveRequestDto;
//	}
	
	private StudentCounsellorFollowupEntity fetchByCriteria(StudentCounsellorFollowupGetRequestDto requestDto) {
		if(requestDto.getCnlgReqId().filter(cnlgReqId -> cnlgReqId > 0).isPresent()) {
				return studentCounsellorFollowupRepository.findById(requestDto.getCnlgReqId().orElse(0L))
						.orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
		}
//		else if (requestDto.getStdCounsellorRequestId().filter(stdCounsellorRequestId -> stdCounsellorRequestId > 0).isPresent()) {
//            return requestDto.getStdCounsellorRequestId()
//                    .map(studentCounsellorFollowupRepository::findByStdCounsellorRequestId)
//                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
//        }
	
		throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
		
	}

	@Override
	public List<StudentCounsellorFollowupResponseDto> retriveData(Long stdCounsellorRequestId) {
		List<StudentCounsellorFollowupResponseDto> response=new ArrayList<StudentCounsellorFollowupResponseDto>();
		
		return studentCounsellorFollowupRepository.findByStdCounsellorRequestId(stdCounsellorRequestId)
				.stream()
				.map(studentCounsellorFollowupDomainMapper::map)
	            .collect(Collectors.toList()) ;
		

	}
}


	
	

