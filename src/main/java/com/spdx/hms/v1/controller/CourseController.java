package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.ICourseMapper;
import com.spdx.hms.v1.model.inbound.request.CourseSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CourseUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CourseResponse;
import com.spdx.hms.v1.service.ICourseService;
import com.spdx.hms.v1.service.dto.request.CourseGetRequestDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/courses", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class CourseController {
    @Autowired
    private ICourseMapper mapper;
    @Autowired
    private ICourseService service;

    @PostMapping
    public CourseResponse create(@Valid @RequestBody CourseSaveRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::save)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving course", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @PutMapping
    public CourseResponse update(@RequestBody CourseUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while updating course", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @GetMapping(path = "/{id}")
    public CourseResponse retrieve(@PathVariable Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(this::map)
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving course", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }
    
    @GetMapping("/all")
    public List<CourseResponse> retrieve() {
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
    
    

    private CourseGetRequestDto map(Long id) {
        return CourseGetRequestDto.builder().courseId(id).build();
    }

}
