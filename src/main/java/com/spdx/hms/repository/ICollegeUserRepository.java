package com.spdx.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.spdx.hms.entity.CollegeUserEntity;


public interface ICollegeUserRepository extends JpaRepository<CollegeUserEntity, Long> ,
JpaSpecificationExecutor<CollegeUserEntity> {
	
	@Modifying
    @Transactional
    @Query("update CollegeUserEntity cu set cu.active = 'N' where cu.id = :id")
    int deactivateCollegeUser(@Param("id")Long id);
    

}
