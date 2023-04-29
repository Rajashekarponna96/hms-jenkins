package com.spdx.hms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "student_admission_payment_transactions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionPaymentTransactionsEntity extends AuditableEntity implements Serializable {
	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long transactionId;

	@Column(name = "stdAdmission_id")
	Long stdAdmissionId;

	@Column(name = "fees")
	Long fees;

	@Column(name = "status")
	String status;

	@Column(name = "payment_request")
	String paymentRequest;

	@Column(name = "payment_response")
	String paymentResponse;

	@Column(name = "payment_Transaction_id")
	String paymentTransactionId;

	@Column(name = "active")
	@Convert(converter = BooleanToStringConverter.class)
	Boolean active;

	@Column(name = "created_by")
	String createdBy;

	@Column(name = "modified_by")
	String modifiedBy;

}
