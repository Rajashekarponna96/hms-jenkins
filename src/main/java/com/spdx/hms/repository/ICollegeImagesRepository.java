package com.spdx.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spdx.hms.entity.CollegeImagesEntity;

@Repository
public interface ICollegeImagesRepository extends JpaRepository<CollegeImagesEntity, Long> {
	
	@Query("SELECT tdv FROM CollegeImagesEntity tdv WHERE tdv.collegeId = :collegeId  ")
	CollegeImagesEntity findByCollegeId(@Param("collegeId") String collegeId );
	
	

	
}
