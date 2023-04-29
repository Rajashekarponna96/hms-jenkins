package com.spdx.hms.repository;

import com.spdx.hms.entity.CollegeEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICollegeRepository extends JpaRepository<CollegeEntity, Long>,
        JpaSpecificationExecutor<CollegeEntity> {

    @Query("SELECT tdv FROM CollegeEntity tdv WHERE LOWER(tdv.collegeCode) = LOWER(:collegeCode)  ")
    CollegeEntity findByCollegeCode(@Param("collegeCode") String collegeCode);
    
    @Query("SELECT tdv FROM CollegeEntity tdv WHERE tdv.collegeId = :collegeId  ")
    List<CollegeEntity> findByCollegeId(@Param("collegeId") Long collegeId);
    
    @Query("SELECT tdv FROM CollegeEntity tdv WHERE tdv.collegeId IN ( :collegeIds ) ")
    List<CollegeEntity> findByCollegeId(@Param("collegeIds") List<Long> collegeIds);


}
