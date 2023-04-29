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
import com.spdx.hms.v1.mapper.ICollegeStudentFollowUpsMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentContactSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentContactUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeStudentContactResponse;
import com.spdx.hms.v1.model.inbound.response.CollegeStudentFollowUpsResponse;
import com.spdx.hms.v1.service.ICollegeStudentFollowUpsService;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsGetRequestDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/colleges/studentfollowups", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class CollegeStudentFollowUpsController {
       
	@Autowired
    private ICollegeStudentFollowUpsMapper mapper;
    @Autowired
    private ICollegeStudentFollowUpsService service;
    
    @PostMapping
    public CollegeStudentFollowUpsResponse create(@Valid @RequestBody CollegeStudentFollowUpsSaveRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::save)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving CollegeStudentFollowUps", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @PutMapping
    public CollegeStudentFollowUpsResponse update(@RequestBody CollegeStudentFollowUpsUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
                    .orElseGet(CollegeStudentFollowUpsResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }
    
    @GetMapping(path = "/{id}")
    public CollegeStudentFollowUpsResponse retrieve(@PathVariable Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(this::map)
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving CollegeStudentFollowUps", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @GetMapping()
    public List<CollegeStudentFollowUpsResponse> retrieve() {
        try {
        	return service.retrieve()
            		.stream()
                    .map(mapper::map)
                    .collect(Collectors.toList());
                   // .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving CollegeStudentFollowUps", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    private CollegeStudentFollowUpsGetRequestDto map(Long id) {
        return CollegeStudentFollowUpsGetRequestDto.builder().followpId(id).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            return Optional.ofNullable(id)
                    .map(Long::valueOf)
                    .map(service::delete)
                    .map(s -> {
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.add(HttpHeaders.LAST_MODIFIED, Instant.now().toString());
                     //   HttpStatus httpStatus = HttpStatus.NO_CONTENT;
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
            log.error("Error occurred while deleting CollegeStudentFollowUps", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
}
