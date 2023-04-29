package com.spdx.hms.v1.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.CounsellorEntity;
import com.spdx.hms.entity.StudentCounsellorEntity;
import com.spdx.hms.mapper.ICounsellorDomainMapper;
import com.spdx.hms.mapper.IStudentCounsellorDomainMapper;
import com.spdx.hms.repository.ICounsellorRepository;
import com.spdx.hms.repository.IStudentCounsellorRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICounsellorService;
import com.spdx.hms.v1.service.dto.request.CounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CounsellorService  implements ICounsellorService{
	
	@Autowired
	private ICounsellorRepository counsellorRepository;
	
	@Autowired
	private ICounsellorDomainMapper  counsellorDomainMapper;
	
	
	@Override
	public CounsellorResponseDto save(CounsellorSaveRequestDto requestDto) {
	try {
        return Optional.ofNullable(requestDto)

                .map(counsellorDomainMapper::map)
                .map(counsellorRepository::save)
                .map(counsellorDomainMapper::map)
                .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
    } catch (HMSException ex) {
        throw ex;
    }catch (Exception ex) {
        log.error("exception occurred while counsellor savin g",ex);
        throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
    }
	
	}

	@Override
	public CounsellorResponseDto update(CounsellorUpdateRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CounsellorUpdateRequestDto::getId)
                    .flatMap(counsellorRepository::findById)
                    .map(CounsellorEntity ->counsellorDomainMapper.map(CounsellorEntity,requestDto))
                    .map(counsellorRepository::save)
                    .map(counsellorDomainMapper::map)
                    .orElseGet(CounsellorResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
            
	}
	
	@Override
	public CounsellorResponseDto retrieve(CounsellorGetRequestDto requestDto) {
		try {
            return Optional.ofNullable(requestDto)
                    .map(this::fetchByCriteria)
                    .map(counsellorDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public List<CounsellorResponseDto> retrieve() {
		try {
//            return 	counsellorRepository.findAll()
			return counsellorRepository.findAllByActive()
            		.stream()
                    .map(counsellorDomainMapper::map)
                    .collect(Collectors.toList())
                    ;
                   // .orElseGet(CourseResponseDto::new);
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0029, ErrorCode.HMS0029.getMessage());
        }
	}

	@Override
	public Boolean delete(Long id) {
		try {
            return Optional.ofNullable(id)
                    .map(counsellorRepository::deactivateCounsellor)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}
	
	private CounsellorEntity fetchByCriteria(CounsellorGetRequestDto requestDto) {
//		if(requestDto.getId().filter(id -> id > 0).isPresent()) {
//			return counsellorRepository.findById(requestDto.getId().orElse(0L))
//					.orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
//}
//		else 
		if (requestDto.getId().filter(Id -> Id > 0).isPresent()) {
          return requestDto.getId()
                  .map(counsellorRepository::findByIdByActive)
                 .orElseThrow(() -> new HMSException(ErrorCode.HMS0029, ErrorCode.HMS0029.getMessage()));
      }
	
		throw new HMSException(ErrorCode.HMS0029, ErrorCode.HMS0029.getMessage());
		
	}


  
}
