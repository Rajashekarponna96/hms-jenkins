package com.spdx.hms.repository;

import com.spdx.hms.entity.StudentQualificationEntity;
import com.spdx.hms.entity.StudentSkillsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface IStudentQualificationRepository extends JpaRepository<StudentQualificationEntity, Long> {

    Collection<StudentQualificationEntity> findByStdProfileId(Long stdProfileId);

    @Modifying
    @Transactional
    @Query("update StudentQualificationEntity se set se.active = 'N' where se.id = :id")
    int deactivateStudentQualification(@Param("id")Long id);
    
    @Query("SELECT se FROM StudentQualificationEntity se where se.student.id = :id ")
    List<StudentQualificationEntity> getByProfileId(@Param("id") Long id);

}
