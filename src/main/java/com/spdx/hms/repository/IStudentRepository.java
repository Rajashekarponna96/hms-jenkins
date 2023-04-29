package com.spdx.hms.repository;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.v1.service.dto.request.StudentPaginationRequestDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Long> ,
        JpaSpecificationExecutor<StudentEntity> {
	
	@Query("SELECT se from StudentEntity se where se.emailId=?1 and se.active='Y'")
    StudentEntity findByEmailId(@Param("emailId")String emailId);
       
    @Query("SELECT se from StudentEntity se where se.mobileNumber=?1 and se.active='Y'")
    StudentEntity findByMobileNumber(@Param("mobileNumber")String mobileNumber);

    @Query("select se from StudentEntity se where (se.emailId=?1 or se.mobileNumber=?2) and se.active='Y'")
    List<StudentEntity> findByEmailIdOrMobileNumber(String emailId, String mobileNumber);
    @Modifying
    @Transactional
    @Query("update StudentEntity se set se.active = 'N' where se.id = :id")
    int deactivateStudent(@Param("id")Long id);
    
    @Query("SELECT tdv FROM StudentEntity tdv WHERE tdv.id IN ( :ids ) ")
    List<StudentEntity> findById(@Param("ids") List<Long> ids);
    
	@Query("SELECT se from StudentEntity se where se.id=?1 and se.active='Y'")
    Optional<StudentEntity> findById(@Param("id")Long id);

}
