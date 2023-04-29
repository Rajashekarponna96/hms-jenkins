package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaymentTransactionsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaymentTransactionsResponseDto;

public interface IStudentAdmissionPaymentTransactionsService {

	StudentAdmissionPaymentTransactionsResponseDto save(StudentAdmissionPaymentTransactionsSaveRequestDto requestDto);

	StudentAdmissionPaymentTransactionsResponseDto update(
			StudentAdmissionPaymentTransactionsUpdateRequestDto requestDto);

	StudentAdmissionPaymentTransactionsResponseDto retrieve(
			StudentAdmissionPaymentTransactionsGetRequestDto requestDto);

	List<StudentAdmissionPaymentTransactionsResponseDto> retrieve();

	Boolean delete(Long id);

}
