package com.spdx.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.CollegeStudentFollowUpsEntity;

public interface ICollegeStudentFollowUpsRepository extends JpaRepository<CollegeStudentFollowUpsEntity, Long>,JpaSpecificationExecutor<CollegeStudentFollowUpsEntity> {
	
	CollegeStudentFollowUpsEntity findByFollowpId(Long followpId); 

	@Modifying
    @Transactional
    @Query("update CollegeStudentFollowUpsEntity csf set csf.active = 'N' where csf.id = :id")
    int deactivateCollegeStudentFollowUps(@Param("id")Long id);

}
