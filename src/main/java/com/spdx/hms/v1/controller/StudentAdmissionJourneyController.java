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
import com.spdx.hms.v1.mapper.IStudentAdmissionJourneyMapper;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionJourneySaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionJourneyUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionJourneyResponse;
import com.spdx.hms.v1.service.IStudentAdmissionJourneyService;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyGetRequestDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/student/admission/journey/", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
@Api(value = "Student Management APIs")
@OpenAPIDefinition(info = @Info(title = "Student Management APIs", description = "Student Management APIs"),
        tags = {@Tag(name = "Student", description = "Student Management APIs",
                externalDocs = @ExternalDocumentation(description = "Student Management APIs"))})
public class StudentAdmissionJourneyController {
	
	
	  @Autowired
	    private IStudentAdmissionJourneyMapper mapper;
	    @Autowired
	    private IStudentAdmissionJourneyService service;
	    
	    
	    @PostMapping
	    public StudentAdmissionJourneyResponse create(@Valid @RequestBody StudentAdmissionJourneySaveRequest request) {
	        try {
	            return Optional.ofNullable(request)
	                    .map(mapper::map)
	                    .map(service::save)
	                    .map(mapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException hmsEx) {
	            throw hmsEx;
	        } catch (Exception ex) {
	            log.error("Error occurred while saving studentAdmissionJourney", ex);
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
	        }
	    }

	    
	    
	    @GetMapping(path = "{id}")
	    public StudentAdmissionJourneyResponse retrieve(@PathVariable String id) {
	       	 try {
	             return Optional.ofNullable(id)
	                     .map(this::map)
	                     .map(service::retrieve)
	                     .map(mapper::map)
	                     .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	         } catch (HMSException hmsEx) {
	             throw hmsEx;
	         } catch (Exception ex) {
	             log.error("Error occurred while retrieving studentAdmissionJourney", ex);
	             throw new HMSException(ErrorCode.HMS0024, ErrorCode.HMS0024.getMessage(), ex.getMessage(), ex);
	         }
	     }
	
	    @GetMapping()
	    public List<StudentAdmissionJourneyResponse> retrieve() {
	        try {
	        	return service.retrieve()
	            		.stream()
	                    .map(mapper::map)
	                    .collect(Collectors.toList());
	                  // .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException hmsEx) {
	            throw hmsEx;
	        } catch (Exception ex) {
	            log.error("Error occurred while retrieving StudentAdmissionJourney", ex);
	            throw new HMSException(ErrorCode.HMS0028, ErrorCode.HMS0028.getMessage(), ex.getMessage(), ex);
	        }
	    }
	    
	    private StudentAdmissionJourneyGetRequestDto map(String id) {
	        return StudentAdmissionJourneyGetRequestDto.builder()
	                .stdProfileId(Long.valueOf(id))
	                .build();
	    }
	    
	    
	    
		   @PutMapping
		    public StudentAdmissionJourneyResponse update(@RequestBody StudentAdmissionJourneyUpdateRequest request) {
		        try {
		            return Optional.ofNullable(request)
		                    .map(mapper::map)
		                    .map(service::update)
		                    .map(mapper::map)
		                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		        } catch (HMSException hmsEx) {
		            throw hmsEx;
		        } catch (Exception ex) {
		            log.error("Error occurred while updating studentAdmissionJourney", ex);
		            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		        }
		    }
		   
		   
		   @DeleteMapping(path = "{id}")
		    public ResponseEntity<String> delete(@PathVariable String id) {
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
		            log.error("Error occurred while deleting studentAdmissionJourney", ex);
		            throw new HMSException(ErrorCode.HMS0028, ErrorCode.HMS0028.getMessage(), ex.getMessage(), ex);
		        }
		        
		    }
		   
	    
}
