package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.spdx.hms.v1.mapper.ICollegeMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegePaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CollegeResponse;
import com.spdx.hms.v1.service.ICollegeService;
import com.spdx.hms.v1.service.dto.request.CollegePaginationRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/colleges", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
@Api(value = "College Management APIs")
@OpenAPIDefinition(info = @Info(title = "College Management APIs", description = "College Management APIs"),
        tags = {@Tag(name = "College", description = "College Management APIs",
                externalDocs = @ExternalDocumentation(description = "College Management APIs"))})
public class CollegeController {

    @Autowired
    private ICollegeService collegeService;

    @Autowired
    private ICollegeMapper mapper;

    @GetMapping(path = "/{collegeId}")
    @Operation(tags = {"College"})
    @ApiOperation(value = "fetch all college Info", consumes = APPLICATION_API_SPIDEX_V1_JSON,
            produces = APPLICATION_API_SPIDEX_V1_JSON, response = CollegeResponse.class)
    public CollegeResponse retrieve(@PathVariable String collegeId) {
        try {
            return Optional.ofNullable(collegeId)
                    .filter(StringUtils::isNotBlank)
                    .map(Long::valueOf)
                    .map(collegeService::retrieve)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving College", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @PostMapping
    @Operation(tags = {"College"})
    @ApiOperation(value = "Create college", response = CollegeResponse.class, tags = "college")
    public CollegeResponse create(@Valid @RequestBody CollegeRequest college) {
        try {
            return Optional.ofNullable(college)
                    .map(mapper::map)
                    .map(collegeService::save)
                    .map(mapper::map)
                    .orElseGet(CollegeResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }

    @PutMapping
    @Operation(tags = {"College"})
    @ApiOperation(value = "Update college", consumes = APPLICATION_API_SPIDEX_V1_JSON,
            produces = APPLICATION_API_SPIDEX_V1_JSON, response = CollegeResponse.class)
    public CollegeResponse update(@RequestBody CollegeUpdateRequest college) {
        try {
            return Optional.ofNullable(college)
                    .map(mapper::map)
                    .map(collegeService::update)
                    .map(mapper::map)
                    .orElseGet(CollegeResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(tags = {"College"})
    @ApiOperation(value = "Delete college", consumes = APPLICATION_API_SPIDEX_V1_JSON,
            produces = APPLICATION_API_SPIDEX_V1_JSON, response = CollegeResponse.class)
    public CollegeResponse delete(@PathVariable("id") long id) {
        try {
            return Optional.of(id)
                    .map(collegeService::delete)
                    .map(mapper::map)
                    .orElseGet(CollegeResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
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
    public CollegePaginationResponse retrieveAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "createdTimestamp") String fields,
            @RequestParam(defaultValue = "-1") Integer direction,
            @MatrixVariable Map<String, String> projectionFields) {
        try {
            Collection<String> sortFields = Stream.of(fields.split(",", -1))
                    .collect(Collectors.toList());
            CollegePaginationRequestDto requestDto = new CollegePaginationRequestDto();
            requestDto.setPage(page);
            requestDto.setSize(size);
            requestDto.setSortFields(sortFields);
            requestDto.setDirection(direction);
            requestDto.setProjectionFields(projectionFields);
            return Optional.of(requestDto)
                    .map(collegeService::retrieveAll)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving student", ex);
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
        return new ResponseEntity<>(errors, HttpStatus.BAD_GATEWAY);
    }
    
    @PostMapping(path = "/list")
    @Operation(tags = {"College"})
    @ApiOperation(value = "fetch all college Info", consumes = APPLICATION_API_SPIDEX_V1_JSON,
            produces = APPLICATION_API_SPIDEX_V1_JSON, response = CollegeResponseDto.class)
    public List<CollegeResponseDto> retrieveList(@RequestBody List<Long> ids) {
        try {
            return 
//            		Optional.ofNullable(ids)
//                    .filter(StringUtils::isNotBlank)
//                    .map(Long::valueOf)
            		collegeService.retrieveList(ids);
//            .map(mapper::map)
//            .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));        } catch (HMSException hmsEx) {
//            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving College", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
		
    }

}
