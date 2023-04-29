package com.spdx.hms.repository;

import com.spdx.hms.entity.AppliedCollegesEntity;
import com.spdx.hms.entity.UniversityEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAppliedCollegesRepository
        extends JpaRepository<AppliedCollegesEntity, Long>, JpaSpecificationExecutor<AppliedCollegesEntity> {

	
	AppliedCollegesEntity findByAppId(long appId);
	@Modifying
    @Transactional
    @Query("update AppliedCollegesEntity ac set ac.active = 'N' where ac.id = :id")
	int deactivateAppliedCollege(@Param("id")Long id);
}
