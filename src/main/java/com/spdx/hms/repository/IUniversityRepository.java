package com.spdx.hms.repository;

import com.spdx.hms.entity.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUniversityRepository extends JpaRepository<UniversityEntity, Long>,JpaSpecificationExecutor<UniversityEntity> {

    //boolean existsByEmailIdOrMobileNumber(String emailId, String mobileNumber);

    //boolean existsByEmailId(String emailId);
	UniversityEntity findByUniversityId(long universityId);
	
	boolean existsByUniversityCode(String universityCode);
	
	@Modifying
    @Transactional
    @Query("update UniversityEntity us set us.active = 'N' where us.id = :id")
    int deactivateUniversity(@Param("id")Long id);
}
