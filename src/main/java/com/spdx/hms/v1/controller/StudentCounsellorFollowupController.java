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
import com.spdx.hms.v1.mapper.IStudentCounsellorFollowupMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentFollowUpsUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorFollowupSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentCounsellorFollowupUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeStudentFollowUpsResponse;
import com.spdx.hms.v1.model.inbound.response.StudentCounsellorFollowupResponse;
import com.spdx.hms.v1.model.inbound.response.StudentCounsellorResponse;
import com.spdx.hms.v1.service.IStudentCounsellorFollowupService;
import com.spdx.hms.v1.service.dto.request.CollegeStudentFollowUpsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorFollowupSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentCounsellorGetRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentCounsellorFollowupResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/student/counsellor/followup/", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class StudentCounsellorFollowupController {
	@Autowired
	IStudentCounsellorFollowupMapper mapper;
	
	@Autowired
	IStudentCounsellorFollowupService service;
	
	@PostMapping
    public StudentCounsellorFollowupResponse create(@Valid @RequestBody StudentCounsellorFollowupSaveRequest request) {
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
	public StudentCounsellorFollowupResponse update(@RequestBody StudentCounsellorFollowupUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
                    .orElseGet(StudentCounsellorFollowupResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }
	
	@GetMapping(path = "/{id}")
    public StudentCounsellorFollowupResponse retrieve(@PathVariable Long id) {
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
	
	@GetMapping("all")
    public List<StudentCounsellorFollowupResponse> retrieve() {
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
            log.error("Error occurred while deleting studentcounsellorfollowup", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
	
	@GetMapping("student/{stdCounsellorRequestId}")
	public List<StudentCounsellorFollowupResponseDto> fetchByStudentId(@PathVariable("stdCounsellorRequestId") Long stdCounsellorRequestId) {
		try {
			return 
//					Optional.ofNullable(stdCounsellorRequestId)
//					.map(this::mapStdCounsellorRequestId)
					service.retriveData(stdCounsellorRequestId);
//					.map(mapper::map)
//                    .orElseGet( StudentCounsellorFollowupResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }
	

	
	
	private StudentCounsellorFollowupGetRequestDto map(Long id) {
        return StudentCounsellorFollowupGetRequestDto.builder().cnlgReqId(id).build();
    }
	
	private StudentCounsellorFollowupGetRequestDto mapStdCounsellorRequestId( Long stdCounsellorRequestId) {
		return StudentCounsellorFollowupGetRequestDto.builder()
			      .stdCounsellorRequestId(stdCounsellorRequestId)
			      .build();
				
	}

	
	

}
