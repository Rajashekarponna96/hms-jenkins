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
import com.spdx.hms.v1.mapper.IStudentQualificationMapper;
import com.spdx.hms.v1.model.inbound.request.StudentQualificationSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentQualificationUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentQualificationResponse;
import com.spdx.hms.v1.service.IStudentQualificationService;
import com.spdx.hms.v1.service.dto.request.StudentQualificationGetRequestDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/students/{student_id}/qualifications", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class StudentQualificationController {
    @Autowired
    private IStudentQualificationMapper mapper;
    @Autowired
    private IStudentQualificationService service;

    @PostMapping
    public StudentQualificationResponse create(
            @PathVariable(value = "student_id") String studentProfileId,
            @Valid @RequestBody StudentQualificationSaveRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(req -> {
                     Optional.ofNullable(studentProfileId)
                     .map(Long::valueOf)
                     .ifPresent(req::setStdProfileId);
                        return req;
                    })
                    .map(mapper::map)
                    .map(service::save)
                    .map(mapper::map)
//                    .orElseGet(StudentQualificationResponse::new);
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving student qualification", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @PostMapping(path="/bulk")
    public List<StudentQualificationResponse> createBulk(
            @PathVariable(value = "student_id") String studentProfileId,
            @Valid @RequestBody List<StudentQualificationSaveRequest> request) {
        try {
            return request
            		.stream()
            		//.map(this::create)
            		.map(req -> {
                        Optional.ofNullable(studentProfileId)
                        .map(Long::valueOf)
                        .ifPresent(req::setStdProfileId);
                           return req;
                       })
            		.map(this::create)
            		.collect(Collectors.toList());
                    
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving student qualification", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    private StudentQualificationResponse create(StudentQualificationSaveRequest request) {
    	return Optional.ofNullable(request)
				.map(mapper::map)
                .map(service::save)
                .map(mapper::map)
                .orElseGet(StudentQualificationResponse::new);
    }
    @PutMapping
    public StudentQualificationResponse update(
            @PathVariable(value = "student_id") Long studentId,
            @RequestBody StudentQualificationUpdateRequest request) {
    	    
        try {
            return Optional.ofNullable(request)
            		
                    .map(req -> {
                        Optional.ofNullable(studentId)
                                .map(Long::valueOf)
                                .ifPresent(req::setStdProfileId);
                        return req;
                    })
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
//                    .orElseGet(StudentQualificationResponse::new);
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while updating student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(
            @PathVariable(value = "student_id") String studentId,
            @PathVariable String id) {
        try {
            return Optional.ofNullable(id)
                    .map(Long::valueOf)
                    .map(service::delete)
                    .map(s -> {
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.add(HttpHeaders.LAST_MODIFIED, Instant.now().toString());

                        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
                        if (BooleanUtils.isFalse(s)) {
                            httpStatus = HttpStatus.NOT_MODIFIED;
                        }
                        return new ResponseEntity<String>(httpHeaders, httpStatus);
                    })
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while deleting student qualification", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @GetMapping(path = "/{std_qualification_id}")
    public StudentQualificationResponse retrieve(
            @PathVariable(value = "student_id") String studentProfileId,
            @PathVariable(value = "std_qualification_id") String stdQualificationId) {
        try {
            return Optional.ofNullable(stdQualificationId)
                    .map(id -> mapRequest(id, studentProfileId))
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseGet(StudentQualificationResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @GetMapping()
    public List<StudentQualificationResponse> retrieve(
            @PathVariable(value = "student_id") String studentProfileId){
        try{
        	return service.retrieve(new Long(studentProfileId))
            		.stream()
                    .map(mapper::map)
                    .collect(Collectors.toList());
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    private StudentQualificationGetRequestDto mapRequest(String id, String studentProfileId) {
        return StudentQualificationGetRequestDto.builder()
                .stdQualificationId(Long.valueOf(id))
                .stdProfileId(Long.valueOf(studentProfileId))
                .build();
    }

}
