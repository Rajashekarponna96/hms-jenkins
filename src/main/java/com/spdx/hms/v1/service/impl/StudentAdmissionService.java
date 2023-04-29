package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spdx.hms.mapper.IStudentAdmissionDomainMapper;
import com.spdx.hms.repository.IStudentAdmissionRepository;
import com.spdx.hms.util.HMSConstants;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentAdmissionService;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionResponseDto;
import com.spdx.hms.v1.util.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentAdmissionService implements IStudentAdmissionService {

	@Autowired
	private IStudentAdmissionRepository studentAdmissiontRepository;
	@Autowired
	private IStudentAdmissionDomainMapper studentAdmissionDomainMapper;

	@Override
	public StudentAdmissionResponseDto save(StudentAdmissionSaveRequestDto requestDto) {
		try {
			return Optional.ofNullable(requestDto).map(studentAdmissionDomainMapper::map)
					.map(studentAdmissiontRepository::save).map(studentAdmissionDomainMapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("exception occured while saving {}", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	@Override
	public StudentAdmissionResponseDto update(StudentAdmissionUpdateRequestDto requestDto) {
		try {
			return Optional.ofNullable(requestDto).flatMap(StudentAdmissionUpdateRequestDto::getStdAdmissionId)
					.flatMap(studentAdmissiontRepository::findById)
					.map(StudentAdmissionEntity -> studentAdmissionDomainMapper.map(StudentAdmissionEntity, requestDto))
					.map(studentAdmissiontRepository::save)
					.map(studentAdmissionDomainMapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	@Override
	public StudentAdmissionResponseDto retrieve(StudentAdmissionGetRequestDto requestDto) {
		try {
			return Optional.ofNullable(requestDto).flatMap(StudentAdmissionGetRequestDto::getStdAdmissionId)
					.flatMap(studentAdmissiontRepository::findById).map(studentAdmissionDomainMapper::map)
					.orElseGet(StudentAdmissionResponseDto::new);
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	@Override
	public List<StudentAdmissionResponseDto> retrieve() {
		try {
			return studentAdmissiontRepository.findAll().stream().map(studentAdmissionDomainMapper::map)
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
			return Optional.ofNullable(id).map(studentAdmissiontRepository::deactivateStudentAdmission)

					.map(d -> d > 0)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}
	
	@Override
    public StudentAdmissionPaginationResponseDto retrieveAll(StudentAdmissionPaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(PaginationUtil.getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                //FIXME
                .map(p -> studentAdmissiontRepository.findAll(PaginationUtil.mapProjections(requestDto,HMSConstants.Student_Admission_CRITERIA_FIELDS
                ), p))
                .map(studentAdmissionDomainMapper::mapPaginationResponse)
                .orElseGet(StudentAdmissionPaginationResponseDto::new);
    }

}
