package com.spdx.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.CounsellorEntity;


public interface ICounsellorRepository extends JpaRepository<CounsellorEntity, Long>, 
JpaSpecificationExecutor<CounsellorEntity> {
	
	
	@Query("select se from CounsellorEntity se where (se.id=?1) and se.active='Y'")
	CounsellorEntity findByIdByActive(@Param("id") Long id);
	
	@Query("select se from CounsellorEntity se where se.active='Y'")
	List<CounsellorEntity> findAllByActive();
	
	@Modifying
    @Transactional
    @Query(value ="update  CounsellorEntity ce set ce.active='N'  where ce.id = :id")
    int deactivateCounsellor(@Param("id") Long id);

}
