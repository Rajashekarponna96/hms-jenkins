package com.spdx.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.CollegeEntity;
import com.spdx.hms.entity.StudentCounsellorEntity;

@Repository
public interface IStudentCounsellorRepository extends JpaRepository<StudentCounsellorEntity, Long>, 
                        JpaSpecificationExecutor<StudentCounsellorEntity> {
	
	boolean existsByCounsellorName(String counsellorName);
	
//	StudentCounsellorEntity findByStudentId(Long studentId);
	
	
//	@Query("SELECT tdv FROM StudentCounsellorEntity tdv WHERE tdv.studentId IN ( :studentId ) ")
	List<StudentCounsellorEntity> findByStudentId(Long studentId);
	
	//StudentCounsellorEntity findByCounsellorId(Long counsellorId);
	List<StudentCounsellorEntity> findByCounsellorId(Long counsellorId);
//	
	@Modifying
    @Transactional
    @Query(value ="update  StudentCounsellorEntity sce set sce.active='N'  where sce.id = :id")
    int deactivateStudentCounsellor(@Param("id") Long id);
 

}
