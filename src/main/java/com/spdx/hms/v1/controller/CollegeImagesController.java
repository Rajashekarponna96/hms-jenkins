package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.ICollegeImagesMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeImagesRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeImagesResponse;
import com.spdx.hms.v1.service.ICollegeImagesService;
import com.spdx.hms.v1.service.dto.request.CollegeImagesRequestDto;

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
@RequestMapping(path="/api/hms/colleges/images/")
@Api(value = "CollegeImages Management APIs")
@OpenAPIDefinition(info = @Info(
        title = "CollegeImages Management APIs",
        description = "CollegeImages Management APIs"
),tags = {
        @Tag(name = "CollegeImages", description = "CollegeImages Management APIs", externalDocs = @ExternalDocumentation(description = "CollegeImages Management APIs")),
})
public class CollegeImagesController {
	
	@Autowired
	private ICollegeImagesService collegeImagesService;
	
	
	@Autowired
	private ICollegeImagesMapper collegeImagesMapper;
	
	@GetMapping
	@Operation(tags = {"CollegeImages"})
	@ApiOperation(value = "fetch all college Info",response = CollegeImagesResponse.class)
	public List<CollegeImagesResponse> get() {
		return collegeImagesService.get()
				.stream()
				.map(collegeImagesMapper::map)
				.collect(Collectors.toList());
	}

	@PostMapping("{collegeId}/{collegeCode}/")
	@Operation(tags = {"CollegeImages"})
	@ApiOperation(value = "Create college Images", response = CollegeImagesResponse.class,tags = "college")
	public List<CollegeImagesResponse> create(@PathVariable("collegeId") Long collegeId,@PathVariable("collegeCode") String collegeCode,@RequestBody List<MultipartFile> file) {
		
		 try {
	            return collegeImagesService.save(CollegeImagesRequestDto.builder()
	            		.collegeId(collegeId).collegeCode(collegeCode).build(),file)
	                    .stream()
	                    .map(collegeImagesMapper::map).collect(Collectors.toList());
	                  //  .orElseGet(CollegeImagesResponse::new);
	        } catch (HMSException hmsEx) {
	            throw hmsEx;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
	        }

	}
	
	@PostMapping("/{collegeId}/upload")
	@Operation(tags = {"OrderDataStatus"})
	@ApiOperation(value = "Create orderData",response = CollegeImagesResponse.class)
	public String upload(@RequestParam("file") MultipartFile file,@PathVariable Long collegeId) {
		return collegeImagesService.saveFile(file);
	}

	/*@PutMapping
	@Operation(tags = {"CollegeImages"})
	@ApiOperation(value = "Update college Images", consumes = APPLICATION_API_SPIDEX_V1_JSON,produces=APPLICATION_API_SPIDEX_V1_JSON , response = CollegeImagesResponse.class)
	public CollegeImagesResponse update(@RequestBody CollegeImagesRequest college) {
		try {
            return Optional.ofNullable(college)
                    .map(collegeImagesMapper::map)
                    .map(collegeImagesService::update)
                    .map(collegeImagesMapper::map)
                    .orElseGet(CollegeImagesResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
	}*/

	@DeleteMapping("{id}")
	@Operation(tags = {"CollegeImages"})
	@ApiOperation(value = "Delete college Images", consumes = APPLICATION_API_SPIDEX_V1_JSON,produces=APPLICATION_API_SPIDEX_V1_JSON , response = CollegeImagesResponse.class)
	public CollegeImagesResponse delete(@PathVariable("id") long id) {
		try {
            return Optional.ofNullable(id)
                    .map(collegeImagesService::delete)
                    .map(collegeImagesMapper::map)
                    .orElseGet(CollegeImagesResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
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
