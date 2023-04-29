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
import com.spdx.hms.v1.mapper.IStudentSkillsMapper;
import com.spdx.hms.v1.model.inbound.request.StudentQualificationSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentSkillsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentSkillsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentQualificationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentSkillsResponse;
import com.spdx.hms.v1.service.IStudentSkillsService;
import com.spdx.hms.v1.service.dto.request.StudentSkillsGetRequestDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/students/{student_id}/skills", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class StudentSkillsController {
    @Autowired
    private IStudentSkillsMapper mapper;
    @Autowired
    private IStudentSkillsService service;

    @PostMapping
    public StudentSkillsResponse create(
            @PathVariable(value = "student_id") String studentProfileId,
            @Valid @RequestBody StudentSkillsSaveRequest request) {
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
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @PostMapping(path="/bulk")
    public List<StudentSkillsResponse> createBulk(
            @PathVariable(value = "student_id") String studentProfileId,
            @Valid @RequestBody List<StudentSkillsSaveRequest> request) {
        try {
        	return request
        			
            		.stream()           		
            		  .map(req -> {
                        Optional.ofNullable(studentProfileId)
                                .map(Long::valueOf)
                                .ifPresent(req::setStdProfileId);
                        return req;
                      
                    })
            		 . map(this::create)
           
            		.collect(Collectors.toList());
                    //.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    private StudentSkillsResponse create(StudentSkillsSaveRequest request ) {
    	return Optional.ofNullable(request)
				.map(mapper::map)
                .map(service::save)
                .map(mapper::map)
                .orElseGet(StudentSkillsResponse::new);
    }

    @PutMapping
    public StudentSkillsResponse update(
            @PathVariable(value = "student_id") String studentProfileId,
            @RequestBody StudentSkillsUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(req -> {
                        Optional.ofNullable(studentProfileId)
                                .map(Long::valueOf)
                                .ifPresent(req::setStdProfileId);
                        return req;
                    })
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while updating student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @GetMapping(path = "/{id}")
    public StudentSkillsResponse retrieve(
            @PathVariable(value = "student_id") String studentProfileId,
            @PathVariable(value = "id") String studentSkillId) {
        try {
            return Optional.ofNullable(studentSkillId)
                    .map(id -> mapRequest(id, studentProfileId))
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @GetMapping()
    public List<StudentSkillsResponse> retrieve(
            @PathVariable(value = "student_id") String studentProfileId) {
        try {
            return service.retrieveByProfileId(new Long(studentProfileId))
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
            log.error("Error occurred while deleting student skill", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    private StudentSkillsGetRequestDto mapRequest(String id, String studentProfileId) {
        return StudentSkillsGetRequestDto.builder()
                .stdSkillId(Long.valueOf(id))
                .stdProfileId(Long.valueOf(studentProfileId))
                .build();
    }

}
