package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spdx.hms.mapper.ICollegeUserDomainMapper;
import com.spdx.hms.repository.ICollegeUserRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICollegeUserService;
import com.spdx.hms.v1.service.dto.request.CollegeUserGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeUserResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CollegeUserService implements ICollegeUserService{
	
	@Autowired
    private ICollegeUserRepository CollegeUserRepository;
    @Autowired
    private ICollegeUserDomainMapper CollegeUserDomainMapper;
    
	@Override
	public CollegeUserResponseDto save(CollegeUserSaveRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    //.map(this::checkEmailOrMobileAlreadyExists)
                    .map(CollegeUserDomainMapper::map)
                    .map(CollegeUserRepository::save)
                    .map(CollegeUserDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occured while saving {}",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public CollegeUserResponseDto update(CollegeUserUpdateRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CollegeUserUpdateRequestDto::getId)
                    .flatMap(CollegeUserRepository::findById)
                    .map(CollegeUserEntity -> CollegeUserDomainMapper.map(CollegeUserEntity, requestDto))
                    .map(CollegeUserRepository::save)
                    .map(CollegeUserDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public CollegeUserResponseDto retrieve(CollegeUserGetRequestDto requestDto) {
		try {
	        return Optional.ofNullable(requestDto)
	                .flatMap(CollegeUserGetRequestDto::getId)
	                .flatMap(CollegeUserRepository::findById)
	                .map(CollegeUserDomainMapper::map)
	                .orElseGet(CollegeUserResponseDto::new);
	    } catch (HMSException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	    }
	}

	@Override
	public List<CollegeUserResponseDto> retrieve() {
		try {
            return 	CollegeUserRepository.findAll()
            		.stream()
                    .map(CollegeUserDomainMapper::map)
                    .collect(Collectors.toList())
                    ;
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public Boolean delete(Long id) {
		try {
	        return Optional.ofNullable(id)
	                .map(CollegeUserRepository::deactivateCollegeUser)
	                .map(d -> d > 0)
	                .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	    } catch (HMSException ex) {
	        throw ex;
	    } catch (Exception ex) {
	        throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	    }
	}

}
