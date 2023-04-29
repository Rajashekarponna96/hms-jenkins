package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.ICollegeCourseMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeCourseRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeCourseResponse;
import com.spdx.hms.v1.service.ICollegeCourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/colleges/courses", consumes = APPLICATION_API_SPIDEX_V1_JSON, produces = APPLICATION_API_SPIDEX_V1_JSON)
@Api(value = "CollegeCourse  Management APIs")
@OpenAPIDefinition(info = @Info(
        title = "CollegeCourse  Management APIs",
        description = "CollegeCourse Course Management APIs"
),tags = {
        @Tag(name = "CollegeCourse", description = "College Course Management APIs", externalDocs = @ExternalDocumentation(description = "CollegeCourse Management APIs")),
})
public class CollegeCourseController {
	
	@Autowired
	private ICollegeCourseService collegeCourseService;
	
	@Autowired
	private ICollegeCourseMapper collegeCourseMapper;
	
	@GetMapping(path = "/{id}")
	@Operation(tags = {"CollegeCourse"})
	@ApiOperation(value = "fetch all college Info by collegecourse id",response = CollegeCourseResponse.class)
	public CollegeCourseResponse get(@PathVariable(value = "id") String collegeCourseId) {
		try {
			return Optional.ofNullable(collegeCourseId)
					.filter(StringUtils::isNotBlank)
					.map(Long::valueOf)
					.map(collegeCourseService::retrieve)
					.map(collegeCourseMapper::map)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			log.error("Error occurred while retrieving CollegeCourse", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		}
	}
	
	@GetMapping(path = "/bycollege/{id}")
	@Operation(tags = {"CollegeCourse"})
	@ApiOperation(value = "fetch all college Info",response = CollegeCourseResponse.class)
	public List<CollegeCourseResponse> getCourses(@PathVariable(value = "id") String collegeId) {
		try {
			return Optional.ofNullable(collegeId)
					.filter(StringUtils::isNotBlank)
					.map(Long::valueOf)
					.map(collegeCourseService::retrieveByCollee)
					.get().stream()
					.map(collegeCourseMapper::map)
					.collect(Collectors.toList());
					//.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (HMSException hmsEx) {
			throw hmsEx;
		} catch (Exception ex) {
			log.error("Error occurred while retrieving CollegeCourse", ex);
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
		}
	}

	@PostMapping("/")
	@Operation(tags = {"CollegeCourse"})
	@ApiOperation(value = "Create college", response = CollegeCourseResponse.class,tags = "college")
	public CollegeCourseResponse create(@Valid @RequestBody CollegeCourseRequest college) {
		
		 try {
	            return Optional.ofNullable(college)
	                    .map(collegeCourseMapper::map)
	                    .map(collegeCourseService::save)
	                    .map(collegeCourseMapper::map)
	                    .orElseGet(CollegeCourseResponse::new);
	        } catch (HMSException hmsEx) {
	            throw hmsEx;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
	        }

	}

	@PutMapping
	@Operation(tags = {"CollegeCourse"})
	@ApiOperation(value = "Update college", consumes = APPLICATION_API_SPIDEX_V1_JSON,produces=APPLICATION_API_SPIDEX_V1_JSON , response = CollegeCourseResponse.class)
	public CollegeCourseResponse update(@RequestBody CollegeCourseRequest college) {
		try {
            return Optional.ofNullable(college)
                    .map(collegeCourseMapper::map)
                    .map(collegeCourseService::update)
                    .map(collegeCourseMapper::map)
                    .orElseGet(CollegeCourseResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
	}

	@DeleteMapping("/{id}")
	@Operation(tags = {"CollegeCourse"})
	@ApiOperation(value = "Delete college", consumes = APPLICATION_API_SPIDEX_V1_JSON,produces=APPLICATION_API_SPIDEX_V1_JSON , response = CollegeCourseResponse.class)
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		try {
			return Optional.ofNullable(id)
					.map(Long::valueOf)
					.map(collegeCourseService::delete)
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
			log.error("Error occurred while deleting student", ex);
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
		log.error("CommonServiceException :: exception", CommonsUtil.getErrorStacktrace(ex));
		Errors errors = new Errors();
		errors.setErrorType("Errors-Support");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
	}

}
