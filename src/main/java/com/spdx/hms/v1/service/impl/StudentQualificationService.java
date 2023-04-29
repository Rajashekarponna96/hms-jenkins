package com.spdx.hms.v1.service.impl;

import com.spdx.hms.mapper.IStudentQualificationDomainMapper;
import com.spdx.hms.repository.IStudentQualificationRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentQualificationService;
import com.spdx.hms.v1.service.dto.request.StudentQualificationGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentQualificationUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentQualificationResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentQualificationService implements IStudentQualificationService {

    @Autowired
    private IStudentQualificationRepository qualificationRepository;

    @Autowired
    private IStudentQualificationDomainMapper qualificationDomainMapper;

    @Override
    public StudentQualificationResponseDto save(StudentQualificationSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::checkQualificationAlreadySaved)
                    .map(qualificationDomainMapper::map)
                    .map(qualificationRepository::save)
                    .map(qualificationDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    private StudentQualificationSaveRequestDto checkQualificationAlreadySaved(
            StudentQualificationSaveRequestDto studentQualificationSaveRequestDto) {

        Long stdProfileId = studentQualificationSaveRequestDto.getStdProfileId().orElseThrow(
                () -> new HMSException(ErrorCode.HMS0007, ErrorCode.HMS0007.getMessage()));
        Collection<StudentQualificationResponseDto>
                studentQualifications = qualificationRepository.findByStdProfileId(stdProfileId)
                .stream()
                .map(qualificationDomainMapper::map)
                .collect(Collectors.toList());
        boolean qualificationExist = studentQualifications
                .stream()
                .map(StudentQualificationResponseDto::getQualification)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(studentQualificationSaveRequestDto.getQualification()
                                  .orElse(StringUtils.EMPTY)::equalsIgnoreCase);
        if (qualificationExist) {
            throw new HMSException(ErrorCode.HMS0008, ErrorCode.HMS0008.getMessage());
        }
        if (studentQualifications.size() > 10) {
            throw new HMSException(ErrorCode.HMS0009, ErrorCode.HMS0009.getMessage());
        }

        return studentQualificationSaveRequestDto;
    }

    @Override
    public StudentQualificationResponseDto update(StudentQualificationUpdateRequestDto requestDto) {
        try {
        	System.out.println("This is service implimentation class");
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentQualificationUpdateRequestDto::getStdQualificationId)
                    .flatMap(qualificationRepository::findById)
                    .map(qualificationEntity -> qualificationDomainMapper.map(qualificationEntity, requestDto))
                    .map(qualificationRepository::save)
                    .map(qualificationDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0031, ErrorCode.HMS0031.getMessage()));
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0031, ErrorCode.HMS0031.getMessage());
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(qualificationRepository::deactivateStudentQualification)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentQualificationResponseDto retrieve(StudentQualificationGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentQualificationGetRequestDto::getStdQualificationId)
                    .flatMap(qualificationRepository::findById)
                    .map(qualificationDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

	@Override
	public List<StudentQualificationResponseDto> retrieve(Long profileId) {
		 try {
	            return qualificationRepository.getByProfileId(profileId)
	            		.stream()
	            		.map(qualificationDomainMapper::map)
	            		.collect(Collectors.toList());
	            		
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	        }
	}

}
