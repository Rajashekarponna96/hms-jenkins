package com.spdx.hms.v1.controller;

import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.IStudentMapper;
import com.spdx.hms.v1.model.inbound.request.StudentSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.IStudentService;
import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentPaginationRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;
import static com.spdx.hms.util.HMSConstants.*;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/students", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
@Api(value = "Student Management APIs")
@OpenAPIDefinition(info = @Info(title = "Student Management APIs", description = "Student Management APIs"),
        tags = {@Tag(name = "Student", description = "Student Management APIs",
                externalDocs = @ExternalDocumentation(description = "Student Management APIs"))})
public class StudentController {

    @Autowired
    private IStudentMapper mapper;
    @Autowired
    private IStudentService service;
    
    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentSaveRequest request) {
        try {
            return Optional.ofNullable(request)
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

    @PutMapping
    public StudentResponse update(@Valid @RequestBody StudentUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
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

    @DeleteMapping(path = "/{id}")
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
            log.error("Error occurred while deleting student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param page -
     * @param size -
     * @param fields -
     * @param criteriaFields -
     * @param direction -
     * @return studentPaginationResponse -
     */
    @GetMapping("/all/{criteriaFields}")
    public StudentPaginationResponse retrieveAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "createdTimestamp") String fields,
            @RequestParam(defaultValue = "-1") Integer direction,
            @MatrixVariable Map<String, String> criteriaFields) {
        try {
            Collection<String> sortFields = Stream.of(fields.split(",", -1))
                    .collect(Collectors.toList());
            StudentPaginationRequestDto requestDto = new StudentPaginationRequestDto();
            requestDto.setPage(page);
            requestDto.setSize(size);
            requestDto.setSortFields(sortFields);
            requestDto.setDirection(direction);
            requestDto.setProjectionFields(criteriaFields);
            return Optional.of(requestDto)
                    .map(service::retrieveAll)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @GetMapping(path = "/{id}")
    public StudentResponse retrieve(@PathVariable String id) {
        try {
            return Optional.ofNullable(id)
                    .map(this::map)
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

    @GetMapping(path = "/email/{emailId}")
    public StudentResponse fetchByEmailId(@PathVariable String emailId) {
        try {
            return Optional.ofNullable(emailId)
                    .map(this::mapEmailId)
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseGet(StudentResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }
    
    @GetMapping(path = "/mobile/{mobileNumber}")
    public StudentResponse fetchByMobileId(@PathVariable String mobileNumber) {
        try {
            return Optional.ofNullable(mobileNumber)
                    .map(this::mapMobileId)
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseGet(StudentResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }


    private StudentGetRequestDto mapEmailId(String emailId) {
        return StudentGetRequestDto.builder()
                .emailId(emailId)
                .build();
    }
    
    private StudentGetRequestDto mapMobileId(String mobileId) {
        return StudentGetRequestDto.builder()
                .mobileNumber(mobileId)
                .build();
    }

    private StudentGetRequestDto map(String id) {
        return StudentGetRequestDto.builder()
                .id(Long.valueOf(id))
                .build();
    }
    
    @PostMapping(path = "/list")
    public List<StudentResponseDto> retrieveList(@RequestBody List<Long> ids) {
        try {
            return service.retrieveList(ids);

        } catch (Exception ex) {
            log.error("Error occurred while retrieving Student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
		
    }

    
    

}
