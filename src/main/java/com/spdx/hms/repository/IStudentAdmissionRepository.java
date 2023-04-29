package com.spdx.hms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spdx.hms.entity.AppliedCollegesEntity;
import com.spdx.hms.entity.StudentAdmissionEntity;

@Repository
public interface IStudentAdmissionRepository extends JpaRepository<StudentAdmissionEntity, Long>,JpaSpecificationExecutor<StudentAdmissionEntity> {

	StudentAdmissionEntity findByStdAdmissionId(long stdAdmissionId);

	StudentAdmissionEntity findByStudentId(long studentId);

	@Query("select csc from StudentAdmissionEntity csc where (csc.stdAdmissionId=?1 or csc.studentId=?2) and csc.active='Y'")
	StudentAdmissionEntity findByStdAdmissionIdAndfindByStudentId(long stdAdmissionId, long studentId);

	@Modifying
	@Transactional
	@Query("update StudentAdmissionEntity csc set csc.active = 'N' where csc.id = :id")
	int deactivateStudentAdmission(@Param("id") Long id);

}