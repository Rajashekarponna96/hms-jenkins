package com.spdx.hms.repository;

import com.spdx.hms.entity.ShortlistedCollegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IShortlistedCollegesRepository
        extends JpaRepository<ShortlistedCollegesEntity, Long>, JpaSpecificationExecutor<ShortlistedCollegesEntity> {


}
