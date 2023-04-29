package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.mapper.IStudentDomainMapper;
import com.spdx.hms.repository.IStudentRepository;
import com.spdx.hms.util.HMSConstants;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.IStudentService;
import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import com.spdx.hms.v1.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IStudentDomainMapper studentDomainMapper;

    @Override
    public StudentResponseDto save(StudentSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::checkEmailOrMobileAlreadyExists)
                    .map(studentDomainMapper::map)
                    .map(studentRepository::save)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Exception occurred while saving", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentResponseDto update(StudentUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(StudentUpdateRequestDto::getId)
                    .flatMap(studentRepository::findById)
                    .map(studentEntity -> studentDomainMapper.map(studentEntity, requestDto))
                    .map(studentRepository::save)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentResponseDto retrieve(StudentGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::fetchByCriteria)
                    .map(studentDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public StudentPaginationResponseDto retrieveAll(StudentPaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                .map(p -> studentRepository.findAll(PaginationUtil.mapProjections(requestDto, HMSConstants.STUDENT_CRITERIA_FIELDS), p))
                .map(studentDomainMapper::mapPaginationResponse)
                .orElseGet(StudentPaginationResponseDto::new);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(studentRepository::deactivateStudent)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private StudentEntity fetchByCriteria(StudentGetRequestDto requestDto) {
        if (requestDto.getId().filter(id -> id > 0).isPresent()) {
            return studentRepository.findById(requestDto.getId().orElse(0L))
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
        } else if (requestDto.getEmailId().filter(StringUtils::isNotBlank).isPresent()) {
            return requestDto.getEmailId()
                    .map(studentRepository::findByEmailId)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
        }else if (requestDto.getMobileNumber().filter(StringUtils::isNotBlank).isPresent()) {
            return requestDto.getMobileNumber()
                    .map(studentRepository::findByMobileNumber)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
        }

        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
    }

    private StudentSaveRequestDto checkEmailOrMobileAlreadyExists(StudentSaveRequestDto requestDto) {
        AtomicBoolean emailExists = new AtomicBoolean(Boolean.FALSE);
        AtomicBoolean mobileExists = new AtomicBoolean(Boolean.FALSE);
        try {
            studentRepository
                    .findByEmailIdOrMobileNumber(requestDto.getEmailId().orElse(""),
                            requestDto.getMobileNumber().orElse(""))
                    .stream()
                    .map(studentDomainMapper::map)
                    .forEach(sr -> {
                        if (sr.getEmailId()
                                .filter(requestDto.getEmailId().orElse(StringUtils.EMPTY)::equalsIgnoreCase)
                                .isPresent()) {
                            emailExists.set(Boolean.TRUE);
                        }
                        if (sr.getMobileNumber().filter(
                                requestDto.getMobileNumber().orElse(StringUtils.EMPTY)::equalsIgnoreCase).isPresent()) {
                            mobileExists.set(Boolean.TRUE);
                        }
                    });
        } catch (DataAccessResourceFailureException ex) {
            log.error("Exception occurred while saving", ex);
            throw new HMSException(ErrorCode.HMS0012, ErrorCode.HMS0012.getMessage());
        }
        if (BooleanUtils.isTrue(emailExists.get()) || BooleanUtils.isTrue(mobileExists.get())) {
            throw new HMSException(ErrorCode.HMS0004, ErrorCode.HMS0004.getMessage());
        }
        return requestDto;
    }

    private Sort.Direction getSortDirection(Integer direction) {
        if (direction > 0) {
            return Sort.Direction.ASC;
        } else if (direction < 0) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
    
    
    @Override
	public List<StudentResponseDto> retrieveList(List<Long> ids) {
		List<StudentResponse> student=new ArrayList<StudentResponse>();

			return studentRepository.findById(ids)
			.stream()
            .map(studentDomainMapper::map)
            .collect(Collectors.toList());

	
		
	}

}
