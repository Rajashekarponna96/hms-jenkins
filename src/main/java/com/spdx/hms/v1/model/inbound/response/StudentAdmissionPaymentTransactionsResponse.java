package com.spdx.hms.v1.model.inbound.response;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionPaymentTransactionsResponse {
	Long transactionId;

	Long stdAdmissionId;

	Long fees;

	String status;

	String paymentRequest;

	String paymentResponse;

	String paymentTransactionId;

	Boolean active;

	String createdBy;

	String modifiedBy;

	Long createdTimestamp;

	Long modifiedTimestamp;

	public Optional<Long> getTransactionId() {
		return Optional.ofNullable(transactionId);
	}

	public Optional<Long> getStdAdmissionId() {
		return Optional.ofNullable(stdAdmissionId);
	}

	public Optional<String> getStatus() {
		return Optional.ofNullable(status);
	}

	public Optional<Long> getFees() {
		return Optional.ofNullable(fees);
	}

	public Optional<String> getPaymentRequest() {
		return Optional.ofNullable(paymentRequest);
	}

	public Optional<String> getPaymentResponse() {
		return Optional.ofNullable(paymentResponse);
	}

	public Optional<Boolean> getActive() {
		return Optional.ofNullable(active);
	}

	public Optional<String> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	public Optional<String> getModifiedBy() {
		return Optional.ofNullable(modifiedBy);
	}

	public Optional<Long> getCreatedTimestamp() {
		return Optional.ofNullable(createdTimestamp);
	}

	public Optional<Long> getModifiedTimestamp() {
		return Optional.ofNullable(modifiedTimestamp);
	}

}
