package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.IStudentCounsellorMapper;
import com.spdx.hms.v1.model.inbound.request.CourseSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CourseUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CourseResponse;
import com.spdx.hms.v1.model.inbound.response.StudentCounsellorResponse;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.IStudentCounsellorService;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/student/counsellor/", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class StudentCounsellorController {
	@Autowired
	IStudentCounsellorMapper mapper;
	
	@Autowired
	IStudentCounsellorService service;
	
	@PostMapping
    public StudentCounsellorResponse create(@Valid @RequestBody StudentCounsellorSaveRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::save)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving StudentCounsellor", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
	
	
	@PutMapping
    public StudentCounsellorResponse update(@Valid @RequestBody StudentCounsellorUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while updating course", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
	
	@GetMapping(path = "{id}")
    public StudentCounsellorResponse retrieve(@PathVariable Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(this::map)
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving course", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
	
	@GetMapping("all")
    public List<StudentCounsellorResponse> retrieve() {
        try {
            return service.retrieve()
            		.stream()
                    .map(mapper::map)
                    .collect(Collectors.toList());
                    //.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving course", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
	
	@DeleteMapping(path = "{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		System.out.println(id);
        try {
            return Optional.ofNullable(id)
                    .map(Long::valueOf)
                    .map(service::delete)
                    .map(s -> {
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.add(HttpHeaders.LAST_MODIFIED, Instant.now().toString());

//                        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
                        HttpStatus httpStatus = HttpStatus.OK;
                        
                        if (BooleanUtils.isFalse(s)) {
                            httpStatus = HttpStatus.NOT_MODIFIED;
                        }
                        return new ResponseEntity<String>(httpHeaders, httpStatus);
                    })
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while deleting studentcounsellor", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

	
	
	
	//get the list of objets  based on studentId
	@GetMapping("student/{studentId}")
	public List<StudentCounsellorResponseDto> dataListFetchByStudentId(@PathVariable("studentId") Long studentId) {
		try {
			return  
//					Optional.ofNullable(studentId)
//					.map(this::mapStudentId)
					service.retriveData(studentId);
//					.map(mapper::map)
//                    .orElseGet(List<StudentCounsellorResponse>::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }
	
	
	
	
	//get the list of objexts based on counsellorId
	@GetMapping("counsellor/{counsellorId}")
	public List<StudentCounsellorResponseDto> dataListFetchByCounsellorId1(@PathVariable("counsellorId") Long counsellorId) {
		try {
			return  
					service.retrieveDataList( counsellorId);
					
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }
					
	
	
	
	private StudentCounsellorGetRequestDto map(Long id) {
        return StudentCounsellorGetRequestDto.builder()
        		.stdCounsellorRequestId(id).build();
    }
	private StudentCounsellorGetRequestDto mapStudentId( Long studentId) {
		return StudentCounsellorGetRequestDto.builder()
			      .studentId(studentId)
			      .build();
				
	}
	
	private StudentCounsellorGetRequestDto mapCounsellorId( Long counsellorId) {
		return StudentCounsellorGetRequestDto.builder()
				.counsellorId(counsellorId)
				.build();
	}

}
