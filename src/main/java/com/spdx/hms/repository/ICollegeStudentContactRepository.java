package com.spdx.hms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.spdx.hms.entity.CollegeStudentContactEntity;

public interface ICollegeStudentContactRepository extends JpaRepository<CollegeStudentContactEntity, Long>,JpaSpecificationExecutor<CollegeStudentContactEntity> {
CollegeStudentContactEntity findByContactId(long contactId);
    
	CollegeStudentContactEntity findByStudentId(long studentId);
    @Query("select csc from CollegeStudentContactEntity csc where (csc.contactId=?1 or csc.studentId=?2) and csc.active='Y'")
    CollegeStudentContactEntity findByContactIdAndStudentId(long contactId, long studentId );
	@Modifying
    @Transactional
    @Query("update CollegeStudentContactEntity csc set csc.active = 'N' where csc.id = :id")
    int deactivateCollegeStudentContact(@Param("id")Long id);

}
