package com.spdx.hms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spdx.hms.entity.StudentAdmissionPaymentTransactionsEntity;

@Repository
public interface IStudentAdmissionPaymentTransactionsRepository
		extends JpaRepository<StudentAdmissionPaymentTransactionsEntity, Long> {

//	StudentAdmissionPaymentTransactionsEntity findByTransactionId(long transactionId);

	// StudentAdmissionEntity findByStdAdmsssionId(long stdAdmsssionId);

	// StudentAdmissionPaymentTransactionsEntity findByStudentId(long studentId);

	StudentAdmissionPaymentTransactionsEntity findByTransactionId(Long transactionId);

//	@Query("select csc from StudentAdmissionPaymentTransactionsEntity csc where (csc.transactionId=?1 or csc.stdAdmsssionId=?2) and csc.active='Y'")
//	StudentAdmissionPaymentTransactionsEntity findByTransactionIdAndfindByTransactionId(long transactionId,
//			long stdAdmsssionId);

	@Modifying
	@Transactional
	@Query("update StudentAdmissionPaymentTransactionsEntity csc set csc.active = 'N' where csc.id = :id")
	int deactivateStudentAdmission(@Param("id") Long id);

}
