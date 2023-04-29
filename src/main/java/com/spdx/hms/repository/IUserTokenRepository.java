package com.spdx.hms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spdx.hms.entity.UserTokenEntity;

@Repository
public interface IUserTokenRepository extends JpaRepository<UserTokenEntity, UUID> {
	
	@Query("SELECT tdv FROM UserTokenEntity tdv WHERE tdv.tokenId = :tokenId and tdv.mobileToken = :mobileToken and tdv.mobileNumber = :mobileNumber and tdv.active = true ")
	UserTokenEntity findByToken(@Param("tokenId") UUID tokenId,@Param("mobileToken") String mobileToken ,@Param("mobileNumber") String mobileNumber);
	
	@Query("SELECT tdv FROM UserTokenEntity tdv WHERE tdv.tokenId = :tokenId and  tdv.mobileNumber = :mobileNumber and tdv.verficationStatus = 'Success' ")
	UserTokenEntity findByTokenValid(@Param("tokenId") UUID tokenId,@Param("mobileNumber") String mobileNumber);
	
	@Query("SELECT tdv FROM UserTokenEntity tdv WHERE  tdv.mobileNumber = :mobileNumber and tdv.tokenId = :tokenId and tdv.verficationStatus = 'Success' ")
	UserTokenEntity findByTokenMobileValid(@Param("mobileNumber") String mobileNumber,@Param("tokenId") UUID tokenId);
	
}
