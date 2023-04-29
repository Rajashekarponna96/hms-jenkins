package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.mapper.IStudentAdmissionPaymentTransactionsDomainMapper;
import com.spdx.hms.repository.IStudentAdmissionPaymentTransactionsRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentAdmissionPaymentTransactionsService;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaymentTransactionsResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentAdmissionPaymentTransactionsService implements IStudentAdmissionPaymentTransactionsService {

	@Autowired
	private IStudentAdmissionPaymentTransactionsRepository studentAdmissionPaymentTransactionsRepository;
	@Autowired
	private IStudentAdmissionPaymentTransactionsDomainMapper studentAdmissionPaymentTransactionsDomainMapper;

	@Override
	public StudentAdmissionPaymentTransactionsResponseDto save(
			StudentAdmissionPaymentTransactionsSaveRequestDto requestDto) {

		try {
			return Optional.ofNullable(requestDto).map(studentAdmissionPaymentTransactionsDomainMapper::map)
					.map(studentAdmissionPaymentTransactionsRepository::save)
					.map(studentAdmissionPaymentTransactionsDomainMapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("exception occured while saving {}", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	@Override
	public StudentAdmissionPaymentTransactionsResponseDto update(
			StudentAdmissionPaymentTransactionsUpdateRequestDto requestDto) {
		try {
			return Optional.ofNullable(requestDto)
					.flatMap(StudentAdmissionPaymentTransactionsUpdateRequestDto::getTransactionId)
					.flatMap(studentAdmissionPaymentTransactionsRepository::findById)
					.map(StudentAdmissionPaymentTransactionsEntity -> studentAdmissionPaymentTransactionsDomainMapper
							.map(StudentAdmissionPaymentTransactionsEntity, requestDto))
					.map(studentAdmissionPaymentTransactionsRepository::save)
					.map(studentAdmissionPaymentTransactionsDomainMapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}

	}

	@Override
	public StudentAdmissionPaymentTransactionsResponseDto retrieve(
			StudentAdmissionPaymentTransactionsGetRequestDto requestDto) {
		try {
			return Optional.ofNullable(requestDto)
					.flatMap(StudentAdmissionPaymentTransactionsGetRequestDto::getTransactionId)
					.flatMap(studentAdmissionPaymentTransactionsRepository::findById)
					.map(studentAdmissionPaymentTransactionsDomainMapper::map)
					.orElseGet(StudentAdmissionPaymentTransactionsResponseDto::new);
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	@Override
	public List<StudentAdmissionPaymentTransactionsResponseDto> retrieve() {
		try {
			return studentAdmissionPaymentTransactionsRepository.findAll().stream()
					.map(studentAdmissionPaymentTransactionsDomainMapper::map).collect(Collectors.toList());
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
					.map(studentAdmissionPaymentTransactionsRepository::deactivateStudentAdmission)

					.map(d -> d > 0)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	
}
