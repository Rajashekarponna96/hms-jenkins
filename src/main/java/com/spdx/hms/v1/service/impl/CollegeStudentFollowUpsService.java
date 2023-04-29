package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.mapper.ICollegeStudentContactDomainMapper;
import com.spdx.hms.mapper.ICollegeStudentFollowUpsDomainMapper;
import com.spdx.hms.repository.ICollegeStudentContactRepository;
import com.spdx.hms.repository.ICollegeStudentFollowUpsRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICollegeStudentContactService;
import com.spdx.hms.v1.service.ICollegeStudentFollowUpsService;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentContactResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentFollowUpsResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CollegeStudentFollowUpsService implements ICollegeStudentFollowUpsService{
   
	@Autowired
    private ICollegeStudentFollowUpsRepository CollegeStudentFollowUpsRepository;
    @Autowired
    private ICollegeStudentFollowUpsDomainMapper CollegeStudentFollowUpsDomainMapper;
    
    @Override   
    public CollegeStudentFollowUpsResponseDto save(CollegeStudentFollowUpsSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    //.map(this::checkEmailOrMobileAlreadyExists)
                    .map(CollegeStudentFollowUpsDomainMapper::map)
                    .map(CollegeStudentFollowUpsRepository::save)
                    .map(CollegeStudentFollowUpsDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occured while saving {}",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
    @Override 
    public CollegeStudentFollowUpsResponseDto update(CollegeStudentFollowUpsUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CollegeStudentFollowUpsUpdateRequestDto::getFollowpId)
                    .flatMap(CollegeStudentFollowUpsRepository::findById)
                    .map(CollegeStudentFollowUpsEntity -> CollegeStudentFollowUpsDomainMapper.map(CollegeStudentFollowUpsEntity, requestDto))
                    .map(CollegeStudentFollowUpsRepository::save)
                    .map(CollegeStudentFollowUpsDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
    
    @Override 
    public List<CollegeStudentFollowUpsResponseDto> retrieve() {
		try {
            return 	CollegeStudentFollowUpsRepository.findAll()
            		.stream()
                    .map(CollegeStudentFollowUpsDomainMapper::map)
                    .collect(Collectors.toList())
                    ;
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}
    @Override
    public CollegeStudentFollowUpsResponseDto retrieve(CollegeStudentFollowUpsGetRequestDto requestDto) {
    	try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CollegeStudentFollowUpsGetRequestDto::getFollowpId)
                    .flatMap(CollegeStudentFollowUpsRepository::findById)
                    .map(CollegeStudentFollowUpsDomainMapper::map)
                    .orElseGet(CollegeStudentFollowUpsResponseDto::new);
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
                    .map(CollegeStudentFollowUpsRepository::deactivateCollegeStudentFollowUps)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
}
