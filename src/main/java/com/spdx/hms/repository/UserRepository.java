package com.spdx.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spdx.hms.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query("SELECT tdv FROM UserEntity tdv WHERE LOWER(tdv.emailId) = LOWER(:userId)  ")
	UserEntity findByUserId(@Param("userId") String userId );
	
	@Query("SELECT tdv FROM UserEntity tdv WHERE LOWER(tdv.emailId) = LOWER(:emailId) and tdv.active='Y' ")
	UserEntity findByEmailId(@Param("emailId") String emailId );
	
	@Query("SELECT tdv FROM UserEntity tdv WHERE tdv.mobileNumber = :mobileNumber and tdv.active='Y' ")
	UserEntity findByMobile(@Param("mobileNumber") String mobileNumber );

	@Query("SELECT ue from UserEntity ue where ue.active='Y'")
	List<UserEntity> findAll();
}
