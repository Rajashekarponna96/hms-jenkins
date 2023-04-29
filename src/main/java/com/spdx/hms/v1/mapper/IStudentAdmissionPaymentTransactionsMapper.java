package com.spdx.hms.v1.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionPaymentTransactionsGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionPaymentTransactionsSavetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionPaymentTransactionsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionPaymentTransactionsResponse;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaymentTransactionsResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,

		implementationName = "IStudentAdmissionPaymentTransactionsImpl")
public interface IStudentAdmissionPaymentTransactionsMapper extends IOptionalMapper {
	@Mappings({ @Mapping(target = "createdTimestamp", ignore = true),
			@Mapping(target = "modifiedTimestamp", ignore = true), @Mapping(target = "active", ignore = true) })

	
	
	StudentAdmissionPaymentTransactionsSaveRequestDto map(StudentAdmissionPaymentTransactionsSavetRequest requestDto);

	
	//	@Mappings({
//        @Mapping(target = "createdTimestamp", ignore = true),
//        @Mapping(target = "modifiedTimestamp", ignore = true),
//        @Mapping(target = "active", ignore = true)
//})
	
	
	
	
	StudentAdmissionPaymentTransactionsUpdateRequestDto map(
			StudentAdmissionPaymentTransactionsUpdateRequest requestDto);

	StudentAdmissionPaymentTransactionsGetRequestDto map(StudentAdmissionPaymentTransactionsGetRequest requestDto);

	StudentAdmissionPaymentTransactionsResponse map(StudentAdmissionPaymentTransactionsResponseDto responseDto);

	default LocalDateTime map(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
	}

	default LocalDateTime map(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		return LocalDateTime.parse(date, dateTimeFormatter);
	}
}
