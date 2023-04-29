package com.spdx.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.spdx.hms.entity.StudentReportingInfoEntity;


@Repository
public interface IStudentReportingInfoRepository extends JpaRepository<StudentReportingInfoEntity, Long>,
JpaSpecificationExecutor<StudentReportingInfoEntity>{
	
		
	@Modifying
    @Transactional
    @Query("update StudentReportingInfoEntity se set se.active = 'N' where se.id = :id")
    int deactivateStudent(@Param("id")Long id);

	 @Query("SELECT std FROM StudentReportingInfoEntity std WHERE std.stdAdmissionId =?1")
	 StudentReportingInfoEntity findByStudentId(long stdAdmissionId );


}
