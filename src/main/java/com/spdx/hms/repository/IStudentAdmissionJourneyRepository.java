package com.spdx.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.StudentAdmissionJourneyEntity;


@Repository
public interface IStudentAdmissionJourneyRepository extends JpaRepository<StudentAdmissionJourneyEntity, Long> {
	
	
	
	@Modifying
    @Transactional
    @Query("update StudentAdmissionJourneyEntity se set se.active = 'N' where se.id = :id")
    int deactivateStudent(@Param("id")Long id);

	
	 @Query("SELECT std FROM StudentAdmissionJourneyEntity std WHERE std.stdProfileId =?1")
	 StudentAdmissionJourneyEntity findByStudentId(long stdProfileId);
	
	
}
