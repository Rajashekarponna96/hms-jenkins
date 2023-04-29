package com.spdx.hms.mapper;

import java.time.Instant;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.StudentAdmissionPaymentTransactionsEntity;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaymentTransactionsResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, implementationName = "IStudentAdmissionPaymentTransactionsDomainMapperImpl")

public interface IStudentAdmissionPaymentTransactionsDomainMapper extends IOptionalMapper {

	StudentAdmissionPaymentTransactionsEntity map(StudentAdmissionPaymentTransactionsSaveRequestDto requestDto);

	@AfterMapping
	default void mapAuditFields(
			@MappingTarget StudentAdmissionPaymentTransactionsEntity studentAdmissionPaymentTransactionsEntity) {
		studentAdmissionPaymentTransactionsEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		studentAdmissionPaymentTransactionsEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	}

	default StudentAdmissionPaymentTransactionsEntity map(StudentAdmissionPaymentTransactionsEntity requestDto,
			StudentAdmissionPaymentTransactionsUpdateRequestDto request) {

		//request.getTransactionId().ifPresent(requestDto::setTransactionId);
		request.getStatus().ifPresent(requestDto::setStatus);
		request.getFees().ifPresent(requestDto::setFees);
		request.getStdAdmissionId().ifPresent(requestDto::setStdAdmissionId);
		request.getPaymentRequest().ifPresent(requestDto::setPaymentRequest);
		request.getPaymentResponse().ifPresent(requestDto::setPaymentResponse);
		request.getCreatedBy().ifPresent(requestDto::setCreatedBy);
		request.getActive().ifPresent(requestDto::setActive);

		request.getModifiedBy().ifPresent(requestDto::setModifiedBy);
		
		request.getCreatedTimestamp().ifPresent(requestDto::setCreatedTimestamp);
		request.getModifiedTimestamp().ifPresent(requestDto::setModifiedTimestamp);
		return requestDto;
	}

	StudentAdmissionPaymentTransactionsEntity map(
			StudentAdmissionPaymentTransactionsUpdateRequestDto StudentAdmissionPaymentTransactionsUpdateRequestDto);

	StudentAdmissionPaymentTransactionsResponseDto map(StudentAdmissionPaymentTransactionsEntity requestDto);

	StudentAdmissionPaymentTransactionsResponseDto map(StudentAdmissionPaymentTransactionsGetRequestDto requestDto);

}
