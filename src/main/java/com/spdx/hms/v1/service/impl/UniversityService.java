package com.spdx.hms.v1.service.impl;

import com.spdx.hms.mapper.IUniversityDomainMapper;
import com.spdx.hms.repository.IStudentRepository;
import com.spdx.hms.repository.IUniversityRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentService;
import com.spdx.hms.v1.service.IUniversityService;
import com.spdx.hms.v1.service.dto.request.*;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import com.spdx.hms.v1.service.dto.response.UniversityResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UniversityService implements IUniversityService {

    @Autowired
    private IUniversityRepository universityRepository;
    @Autowired
    private IUniversityDomainMapper universityDomainMapper;

    @Override
    public UniversityResponseDto save(UniversitySaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::checkUniversityCodeAlreadyExist)
                    .map(universityDomainMapper::map)
                    .map(universityRepository::save)
                    .map(universityDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occured while saving {}",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public UniversityResponseDto update(UniversityUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(UniversityUpdateRequestDto::getUniversityId)
                    .flatMap(universityRepository::findById)
                    .map(universityEntity -> universityDomainMapper.map(universityEntity, requestDto))
                    .map(universityRepository::save)
                    .map(universityDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0025, ErrorCode.HMS0025.getMessage()));
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public UniversityResponseDto retrieve(UniversityGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
            		.flatMap(UniversityGetRequestDto::getUniversityId)
            		.flatMap(universityRepository::findById)
                    .map(universityDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0025, ErrorCode.HMS0025.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

	@Override
	public List<UniversityResponseDto> retrieve() {
		try {
            return 	universityRepository.findAll()
            		.stream()
                    .map(universityDomainMapper::map)
                    .collect(Collectors.toList())
                    ;
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
	                .map(universityRepository::deactivateUniversity)
	                .map(d -> d > 0)
	                .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	    } catch (HMSException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	    }
	}
   
	private UniversitySaveRequestDto checkUniversityCodeAlreadyExist (
            UniversitySaveRequestDto universitySaveRequestDto) {
        String universityCode = universitySaveRequestDto.getUniversityCode().orElseThrow(
                () -> new HMSException(ErrorCode.HMS0026, ErrorCode.HMS0026.getMessage()));
        boolean universityCodeExists = universityRepository.existsByUniversityCode(universityCode);
        if (universityCodeExists) {
            throw new HMSException(ErrorCode.HMS0027, ErrorCode.HMS0027.getMessage());
        }


        return universitySaveRequestDto;
    }
	
	
    /*private Optional<StudentEntity> fetchByCriteria(StudentGetRequestDto requestDto) {
        if (requestDto.getId().filter(id -> id > 0).isPresent()) {
            return studentRepository.findById(requestDto.getId().orElse(0L));
        } else if (requestDto.getEmailId().filter(StringUtils::isNotBlank).isPresent()) {
            return requestDto.getEmailId()
                    .map(studentRepository::findByEmailId);
        }

        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
    }

    private StudentSaveRequestDto checkEmailOrMobileAlreadyExists(StudentSaveRequestDto requestDto) {
        boolean isUserExists = studentRepository
            .existsByEmailId(
                requestDto.getEmailId().orElse("")
            );
        if (isUserExists){
            throw new HMSException(ErrorCode.HMS0004, ErrorCode.HMS0004.getMessage());
        }
        return requestDto;
    }*/
}
