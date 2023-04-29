package com.spdx.hms.repository;

import com.spdx.hms.entity.CollegeCreditsEntity;
import com.spdx.hms.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICollegeCreditsRepository
        extends JpaRepository<CollegeCreditsEntity, Long>, JpaSpecificationExecutor<CollegeCreditsEntity> {

    @Query("SELECT tdv FROM CollegeCreditsEntity tdv WHERE tdv.collegeId=?1")
    CollegeCreditsEntity findByCollegeId(long collegeId);
}
