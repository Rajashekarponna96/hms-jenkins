package com.spdx.hms.repository;

import com.spdx.hms.entity.StudentExperienceEntity;
import com.spdx.hms.entity.StudentQualificationEntity;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IStudentExperienceRepository extends JpaRepository<StudentExperienceEntity, Long> {

    StudentExperienceEntity findByStdExprIdAndStdProfileId(Long stdExprId,Long stdProfileId);
    
    Collection<StudentExperienceEntity> findByStdProfileId(Long stdProfileId);

    @Modifying
    @Transactional
    @Query("update StudentExperienceEntity se set se.active = 'N' where se.id = :id")
    int deactivateStudentExperience(@Param("id") Long id);

}
