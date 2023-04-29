package com.spdx.hms.v1.controller;

import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.ICounsellorQuestionnaireReportMapper;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireReportSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CounsellorQuestionnaireReportUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CounsellorQuestionnaireReportPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CounsellorQuestionnaireReportResponse;
import com.spdx.hms.v1.service.ICounsellorQuestionnaireReportService;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportPaginationRequestDto;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

@RestController
@Slf4j
@RequestMapping(path = "/api/hms/questionnaires/reports/", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class CounsellorQuestionnaireReportController {
    @Autowired
    private ICounsellorQuestionnaireReportMapper mapper;
    @Autowired
    private ICounsellorQuestionnaireReportService service;

    @PostMapping
    public CounsellorQuestionnaireReportResponse create(
            @Valid @RequestBody CounsellorQuestionnaireReportSaveRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::save)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving CounsellorQuestionnaireReport", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @PutMapping
    public CounsellorQuestionnaireReportResponse update(
            @RequestBody CounsellorQuestionnaireReportUpdateRequest request) {
        try {
            return Optional.ofNullable(request)
                    .map(mapper::map)
                    .map(service::update)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while updating CounsellorQuestionnaireReport", ex);
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
            log.error("Error occurred while deleting CounsellorQuestionnaireReport", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    /**
     * @param page           -
     * @param size           -
     * @param fields         -
     * @param criteriaFields -
     * @param direction      -
     * @return CounsellorQuestionnaireReportPaginationResponse -
     */
    @GetMapping("all/{criteriaFields}")
    public CounsellorQuestionnaireReportPaginationResponse retrieveAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "createdTimestamp") String fields,
            @RequestParam(defaultValue = "-1") Integer direction,
            @MatrixVariable Map<String, String> criteriaFields) {
        try {
            Collection<String> sortFields = Stream.of(fields.split(",", -1))
                    .collect(Collectors.toList());
            CounsellorQuestionnaireReportPaginationRequestDto requestDto =
                    CounsellorQuestionnaireReportPaginationRequestDto.builder()
                            .page(page)
                            .size(size)
                            .sortFields(sortFields)
                            .direction(direction)
                            .projectionFields(criteriaFields)
                            .build();
            return Optional.ofNullable(requestDto)
                    .map(service::retrieveAll)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving CounsellorQuestionnaireReport", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @GetMapping(path = "{id}")
    public CounsellorQuestionnaireReportResponse retrieve(@PathVariable String id) {
        try {
            return Optional.ofNullable(id)
                    .map(this::map)
                    .map(service::retrieve)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving CounsellorQuestionnaireReport", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }


    private CounsellorQuestionnaireReportGetRequestDto map(String id) {
        return CounsellorQuestionnaireReportGetRequestDto.builder()
                .questionnaireId(Long.valueOf(id))
                .build();
    }

}
