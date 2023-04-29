package com.spdx.hms.repository;

import com.spdx.hms.entity.CollegeCourseEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spdx.hms.entity.CollegeCourseEntity;

@Repository
public interface ICollegeCourseRepository extends JpaRepository<CollegeCourseEntity, Long> {

    @Query("SELECT tdv FROM CollegeCourseEntity tdv WHERE tdv.collegeId =?1 and tdv.courseId =?2")
    CollegeCourseEntity findByCollegeNCourse(long collegeId, long courseId);

    @Query("SELECT tdv FROM CollegeCourseEntity tdv WHERE tdv.collegeId =?1")
    List<CollegeCourseEntity> findByCollege(long collegeId);

    CollegeCourseEntity findByCollegeIdAndCourseId(long collegeId, long courseId);

    @Modifying
    @Transactional
    @Query("update CollegeCourseEntity cce set cce.active = 'N' where cce.id = :id")
    int deactivateCollegeCourse(@Param("id") Long id);

}
