package com.spdx.hms.v1.service.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spdx.hms.mapper.ICollegeStudentContactDomainMapper;
import com.spdx.hms.repository.ICollegeStudentContactRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICollegeStudentContactService;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentContactResponseDto;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CollegeStudentContactService implements ICollegeStudentContactService{
     
	@Autowired
    private ICollegeStudentContactRepository CollegeStudentContactRepository;
    @Autowired
    private ICollegeStudentContactDomainMapper CollegeStudentContactDomainMapper;
    
    @Override   
    public CollegeStudentContactResponseDto save(CollegeStudentContactSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    //.map(this::checkEmailOrMobileAlreadyExists)
                    .map(CollegeStudentContactDomainMapper::map)
                    .map(CollegeStudentContactRepository::save)
                    .map(CollegeStudentContactDomainMapper::map)
                    .orElseThrow( () -> new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error("exception occured while saving {}",ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
    @Override 
    public CollegeStudentContactResponseDto update(CollegeStudentContactUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CollegeStudentContactUpdateRequestDto::getContactId)
                    .flatMap(CollegeStudentContactRepository::findById)
                    .map(CollegeStudentContactEntity -> CollegeStudentContactDomainMapper.map(CollegeStudentContactEntity, requestDto))
                    .map(CollegeStudentContactRepository::save)
                    .map(CollegeStudentContactDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0015, ErrorCode.HMS0015.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
  
   @Override 
    public List<CollegeStudentContactResponseDto> retrieve() {
		try {
            return 	CollegeStudentContactRepository.findAll()
            		.stream()
                    .map(CollegeStudentContactDomainMapper::map)
                    .collect(Collectors.toList())
                    ;
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}
@Override
public CollegeStudentContactResponseDto retrieve(CollegeStudentContactGetRequestDto requestDto) {
	try {
        return Optional.ofNullable(requestDto)
                .flatMap(CollegeStudentContactGetRequestDto::getContactId)
                .flatMap(CollegeStudentContactRepository::findById)
                .map(CollegeStudentContactDomainMapper::map)
                .orElseGet(CollegeStudentContactResponseDto::new);
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
                .map(CollegeStudentContactRepository::deactivateCollegeStudentContact)
                .map(d -> d > 0)
                .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
    } catch (HMSException ex) {
        throw ex;
    } catch (Exception ex) {
        throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
    }
}
}
