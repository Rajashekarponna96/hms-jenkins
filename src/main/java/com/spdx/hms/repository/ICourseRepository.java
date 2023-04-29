package com.spdx.hms.repository;

import com.spdx.hms.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<CourseEntity, Long> {

   boolean existsByCourseName(String courseName);

}
