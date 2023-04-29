package com.spdx.hms.repository;

import com.spdx.hms.entity.CounsellorQuestionnaireReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICounsellorQuestionnaireReportRepository
        extends JpaRepository<CounsellorQuestionnaireReportEntity, Long>,
        JpaSpecificationExecutor<CounsellorQuestionnaireReportEntity> {
    @Modifying
    @Transactional
    @Query("update CounsellorQuestionnaireReportEntity se set se.active = 'N' where se.id = :id")
    int deactivateCounsellorQuestionnaireReport(@Param("id") Long id);

}
