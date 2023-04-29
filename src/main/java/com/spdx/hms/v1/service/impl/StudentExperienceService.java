package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.StudentExperienceEntity;
import com.spdx.hms.mapper.IStudentExperienceDomainMapper;
import com.spdx.hms.repository.IStudentExperienceRepository;
import com.spdx.hms.util.EntityRefreshUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentExperienceService;
import com.spdx.hms.v1.service.dto.request.StudentExperienceGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentExperienceUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentExperienceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class StudentExperienceService implements IStudentExperienceService {

    @Autowired
    private IStudentExperienceRepository studentRepository;
    @Autowired
    private IStudentExperienceDomainMapper studentDomainMapper;
    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public StudentExperienceResponseDto save(StudentExperienceSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(studentDomainMapper::map)
                    .map(studentRepository::save)
                    .map(s -> EntityRefreshUtil.refresh(entityManager, s, StudentExperienceEntity.class))
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            log.error("DataIntegrityViolationException error while saving", ex);
            throw new HMSException(ErrorCode.HMS0021, ErrorCode.HMS0021.getMessage());
        }catch (Exception ex) {
            log.error("exception occurred while saving", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentExperienceResponseDto update(StudentExperienceUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(se -> studentRepository.findByStdExprIdAndStdProfileId(requestDto.getStdExprId().orElse(0L),
                            requestDto.getStdProfileId().orElse(0L)))
                    .map(studentEntity -> studentDomainMapper.map(studentEntity, requestDto))
                    .map(studentRepository::save)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentExperienceResponseDto retrieve(StudentExperienceGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(se -> studentRepository.findByStdExprIdAndStdProfileId(requestDto.getStdExprId().orElse(0L),
                            requestDto.getStdProfileId().orElse(0L)))
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
                    .map(studentRepository::deactivateStudentExperience)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

	@Override
	public List<StudentExperienceResponseDto> retrieve(Long studentId) {
		 try {
	            return studentRepository.findByStdProfileId(studentId)
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
