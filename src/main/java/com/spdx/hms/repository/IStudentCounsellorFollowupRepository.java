package com.spdx.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.StudentCounsellorFollowupEntity;



@Repository
public interface IStudentCounsellorFollowupRepository extends JpaRepository<StudentCounsellorFollowupEntity, Long>,
                                             JpaSpecificationExecutor<StudentCounsellorFollowupEntity>{
	
//	boolean existsByStdCounsellorRequestId(Long stdCounsellorRequestId );
	
	//StudentCounsellorFollowupEntity findByStdCounsellorRequestId(Long stdCounsellorRequestId);
	List<StudentCounsellorFollowupEntity> findByStdCounsellorRequestId(Long stdCounsellorRequestId);
	
	@Modifying
    @Transactional
    @Query(value ="update  StudentCounsellorFollowupEntity scf set scf.active='N'  where scf.id = :id")
    int deactivateStudentCounsellorFollowup(@Param("id") Long id);

}
