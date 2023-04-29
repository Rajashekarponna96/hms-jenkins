package com.spdx.hms.repository;

import com.spdx.hms.entity.CounsellorQuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICounsellorQuestionnaireRepository extends JpaRepository<CounsellorQuestionnaireEntity, Long>,
        JpaSpecificationExecutor<CounsellorQuestionnaireEntity> {
    @Modifying
    @Transactional
    @Query("update CounsellorQuestionnaireEntity se set se.active = 'N' where se.id = :id")
    int deactivateCounsellorQuestionnaire(@Param("id")Long id);

}
