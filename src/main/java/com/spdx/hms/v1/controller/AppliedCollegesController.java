package com.spdx.hms.v1.controller;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.IAppliedCollegesMapper;
import com.spdx.hms.v1.model.inbound.request.AppliedCollegesSaveRequest;
import com.spdx.hms.v1.model.inbound.request.AppliedCollegesUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.AppliedCollegesPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.AppliedCollegesResponse;
import com.spdx.hms.v1.service.IAppliedCollegesService;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesPaginationRequestDto;
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


@RestController
@Slf4j
@RequestMapping(path = "/api/hms/colleges/applied", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class AppliedCollegesController {

    @Autowired
    private IAppliedCollegesService appliedCollegesService;

    @Autowired
    private IAppliedCollegesMapper mapper;

    @GetMapping
    public List<AppliedCollegesResponse> get() {
        return appliedCollegesService.get()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping
    public AppliedCollegesResponse create(@Valid @RequestBody AppliedCollegesSaveRequest AppliedColleges) {

        try {
            return Optional.ofNullable(AppliedColleges)
                    .map(mapper::map)
                    .map(appliedCollegesService::save)
                    .map(mapper::map)
                    .orElseGet(AppliedCollegesResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }

    }

    @PutMapping
    public AppliedCollegesResponse update(@RequestBody AppliedCollegesUpdateRequest AppliedColleges) {
        try {
            return Optional.ofNullable(AppliedColleges)
                    .map(mapper::map)
                    .map(appliedCollegesService::update)
                    .map(mapper::map)
                    .orElseGet(AppliedCollegesResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            return Optional.ofNullable(id)
                    .map(Long::valueOf)
                    .map(appliedCollegesService::delete)
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
            log.error("Error occurred while deleting student", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
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
    public AppliedCollegesPaginationResponse retrieveAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "createdTimestamp") String fields,
            @RequestParam(defaultValue = "-1") Integer direction,
            @MatrixVariable Map<String, String> projectionFields) {
        try {
            Collection<String> sortFields = Stream.of(fields.split(",", -1))
                    .collect(Collectors.toList());
            AppliedCollegesPaginationRequestDto requestDto = new AppliedCollegesPaginationRequestDto();
            requestDto.setPage(page);
            requestDto.setSize(size);
            requestDto.setSortFields(sortFields);
            requestDto.setDirection(direction);
            requestDto.setProjectionFields(projectionFields);
            return Optional.of(requestDto)
                    .map(appliedCollegesService::retrieveAll)
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
        return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
    }

}
