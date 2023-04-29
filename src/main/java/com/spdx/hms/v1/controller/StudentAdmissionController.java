package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.IStudentAdmissionMapper;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionSavetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.AppliedCollegesPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionResponse;
import com.spdx.hms.v1.service.IStudentAdmissionService;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionPaginationRequestDto;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/students/studentadmission/", consumes = APPLICATION_API_SPIDEX_V1_JSON, produces = APPLICATION_API_SPIDEX_V1_JSON)
@Api(value = "Student Management APIs")
@OpenAPIDefinition(info = @Info(title = "Student  Management APIs", description = "Student Management APIs"), tags = {
		@Tag(name = "Student", description = "Student Management APIs", externalDocs = @ExternalDocumentation(description = "Student Management APIs")) })

public class StudentAdmissionController {
	@Autowired
	private IStudentAdmissionMapper mapper;
	@Autowired
	private IStudentAdmissionService service;

	@PostMapping
	public StudentAdmissionResponse create(@Valid @RequestBody StudentAdmissionSavetRequest request) {
		try {
			return Optional.ofNullable(request).map(mapper::map).map(service::save).map(mapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			log.error("Error occurred while saving StudentAdmission", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		}
	}

	@GetMapping(path = "/{id}")
	public StudentAdmissionResponse retrieve(@PathVariable Long id) {
		try {
			return Optional.ofNullable(id).map(this::map).map(service::retrieve).map(mapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			log.error("Error occurred while retrieving StudentAdmission", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		}
	}

	@GetMapping()
	public List<StudentAdmissionResponse> retrieve() {
		try {
			return service.retrieve().stream().map(mapper::map).collect(Collectors.toList());
			// .orElseThrow(() -> new HMSException(ErrorCode.HMS0005,
			// ErrorCode.HMS0005.getMessage()));
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			log.error("Error occurred while retrieving StudentAdmission", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		}
	}

	@PutMapping
	public StudentAdmissionResponse update(@RequestBody StudentAdmissionUpdateRequest request) {
		try {
			return Optional.ofNullable(request)
					.map(mapper::map)
					.map(service::update)
					.map(mapper::map)
					.orElseGet(StudentAdmissionResponse::new);
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
		}
	}

	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		try {
			return Optional.ofNullable(id).map(Long::valueOf).map(service::delete).map(s -> {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add(HttpHeaders.LAST_MODIFIED, Instant.now().toString());

				// HttpStatus httpStatus = HttpStatus.NO_CONTENT;
				HttpStatus httpStatus = HttpStatus.OK;
				if (BooleanUtils.isFalse(s)) {
					httpStatus = HttpStatus.NOT_MODIFIED;
				}
				return new ResponseEntity<String>(httpHeaders, httpStatus);
			}).orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			log.error("Error occurred while deleting StudentAdmission", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		}
	}

	
 	 private StudentAdmissionGetRequestDto map(Long id) {
		         return StudentAdmissionGetRequestDto.builder().stdAdmissionId(id).build();
				 
		    }
 	 
 	 /**
      * @param page             -
      * @param size             -
      * @param fields           -
      * @param projectionFields -
      * @param direction        -
      * @return studentPaginationResponse -
      */
     @GetMapping("/all/{projectionFields}")
     public StudentAdmissionPaginationResponse retrieveAll(
             @RequestParam(defaultValue = "0") Integer page,
             @RequestParam(defaultValue = "3") Integer size,
             @RequestParam(defaultValue = "createdTimestamp") String fields,
             @RequestParam(defaultValue = "-1") Integer direction,
             @MatrixVariable Map<String, String> projectionFields) {
         try {
             Collection<String> sortFields = Stream.of(fields.split(",", -1))
                     .collect(Collectors.toList());
             StudentAdmissionPaginationRequestDto requestDto = new StudentAdmissionPaginationRequestDto();
             requestDto.setPage(page);
             requestDto.setSize(size);
             requestDto.setSortFields(sortFields);
             requestDto.setDirection(direction);
             requestDto.setProjectionFields(projectionFields);
             return Optional.of(requestDto)
                     .map(service::retrieveAll)
                     .map(mapper::map)
                     .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
         } catch (HMSException hmsEx) {
             throw hmsEx;
         } catch (Exception ex) {
             log.error("Error occurred while retrieving StudentAdmission", ex);
             throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
         }
     }


     /**
      * exceptionHandler CommonServiceException
      *
      * @param ex
      * @return
      */
     @ExceptionHandler(CommonServiceException.class)
     public ResponseEntity<Errors> exceptionHandler(Exception ex) {
         log.error("CommonServiceException :: exception {}", CommonsUtil.getErrorStacktrace(ex));
         Errors errors = new Errors();
         errors.setErrorType("Errors-Support");
         errors.setErrorMessage(ex.getMessage());
         return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
     }


}
