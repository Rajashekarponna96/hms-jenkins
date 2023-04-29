package com.spdx.hms.v1.controller;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.ICollegeCreditsMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeCreditsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeCreditsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeCreditsPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.CollegeCreditsResponse;
import com.spdx.hms.v1.service.ICollegeCreditsService;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsPaginationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;


@RestController
@Slf4j
@RequestMapping(path = "/api/hms/colleges/credits", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class CollegeCreditsController {

    @Autowired
    private ICollegeCreditsService collegeCreditsService;

    @Autowired
    private ICollegeCreditsMapper mapper;

    @GetMapping(path = "/{id}")
    public CollegeCreditsResponse get(@PathVariable(value = "id") String collegeId) {
        try {
            return Optional.ofNullable(collegeId)
                    .filter(StringUtils::isNotBlank)
                    .map(Long::valueOf)
                    .map(collegeCreditsService::get)
                    .map(mapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while retrieving CollegeCredit", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
    }

    @PostMapping
    public CollegeCreditsResponse create(@Valid @RequestBody CollegeCreditsSaveRequest CollegeCredits) {

        try {
            return Optional.ofNullable(CollegeCredits)
                    .map(mapper::map)
                    .map(collegeCreditsService::save)
                    .map(mapper::map)
                    .orElseGet(CollegeCreditsResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }

    }

    @PutMapping
    public CollegeCreditsResponse update(@RequestBody CollegeCreditsUpdateRequest CollegeCredits) {
        try {
            return Optional.ofNullable(CollegeCredits)
                    .map(mapper::map)
                    .map(collegeCreditsService::update)
                    .map(mapper::map)
                    .orElseGet(CollegeCreditsResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public CollegeCreditsResponse delete(@PathVariable("id") long id) {
        try {
            return Optional.of(id)
                    .map(collegeCreditsService::delete)
                    .map(mapper::map)
                    .orElseGet(CollegeCreditsResponse::new);
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
    public CollegeCreditsPaginationResponse retrieveAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "createdTimestamp") String fields,
            @RequestParam(defaultValue = "-1") Integer direction,
            @MatrixVariable Map<String, String> projectionFields) {
        try {
            Collection<String> sortFields = Stream.of(fields.split(",", -1))
                    .collect(Collectors.toList());
            CollegeCreditsPaginationRequestDto requestDto = new CollegeCreditsPaginationRequestDto();
            requestDto.setPage(page);
            requestDto.setSize(size);
            requestDto.setSortFields(sortFields);
            requestDto.setDirection(direction);
            requestDto.setProjectionFields(projectionFields);
            return Optional.of(requestDto)
                    .map(collegeCreditsService::retrieveAll)
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
