package com.spdx.hms.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.StudentQualificationEntity;
import com.spdx.hms.entity.StudentSkillsEntity;

@Repository
public interface IStudentSkillsRepository extends JpaRepository<StudentSkillsEntity, Long> {

    @Modifying
    @Transactional
    @Query("update StudentSkillsEntity se set se.active = 'N' where se.id = :id")
    int deactivateStudentSkill(@Param("id") Long id);
    
    @Query("SELECT se FROM StudentSkillsEntity se where se.student.id = :id ")
    List<StudentSkillsEntity> getByProfileId(@Param("id") Long id);
    
    Collection<StudentSkillsEntity> findByStdProfileId(Long stdProfileId);
    

}
