package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.mapper.IStudentSkillsDomainMapper;
import com.spdx.hms.repository.IStudentRepository;
import com.spdx.hms.repository.IStudentSkillsRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentSkillsService;
import com.spdx.hms.v1.service.dto.request.StudentSkillsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentSkillsResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentSkillsService implements IStudentSkillsService {

    @Autowired
    private IStudentSkillsRepository studentSkillRepository;
    
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IStudentSkillsDomainMapper studentDomainMapper;

    @Override
    public StudentSkillsResponseDto save(StudentSkillsSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(studentDomainMapper::map)
                    .map(studentSkillRepository::save)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("exception occurred while saving", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentSkillsResponseDto update(StudentSkillsUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentSkillsUpdateRequestDto::getStdSkillId)
                    .flatMap(studentSkillRepository::findById)
                    .map(studentEntity -> studentDomainMapper.map(studentEntity, requestDto))
                    .map(studentSkillRepository::save)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentSkillsResponseDto retrieve(StudentSkillsGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentSkillsGetRequestDto::getStdSkillId)
                    .flatMap(studentSkillRepository::findById)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
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
                    .map(studentSkillRepository::deactivateStudentSkill)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

	@Override
	public List<StudentSkillsResponseDto> retrieveByProfileId(Long profileId) {
		 try {
	            return studentSkillRepository.findByStdProfileId(profileId)
	            		.stream()
	            		.map(studentDomainMapper::map)
	            		.collect(Collectors.toList());
	            		
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	        }
	}


}
