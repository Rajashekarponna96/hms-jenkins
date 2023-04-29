package com.spdx.hms.v1.controller;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.IShortlistedCollegesMapper;
import com.spdx.hms.v1.model.inbound.request.ShortlistedCollegesSaveRequest;
import com.spdx.hms.v1.model.inbound.request.ShortlistedCollegesUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.ShortlistedCollegesPaginationResponse;
import com.spdx.hms.v1.model.inbound.response.ShortlistedCollegesResponse;
import com.spdx.hms.v1.service.IShortlistedCollegesService;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesPaginationRequestDto;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(path = "/api/hms/colleges/shortlisted", consumes = APPLICATION_API_SPIDEX_V1_JSON,
        produces = APPLICATION_API_SPIDEX_V1_JSON)
public class ShortlistedCollegesController {

    @Autowired
    private IShortlistedCollegesService shortlistedCollegesService;

    @Autowired
    private IShortlistedCollegesMapper mapper;

    @GetMapping
    public List<ShortlistedCollegesResponse> get() {
        return shortlistedCollegesService.get()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShortlistedCollegesResponse create(@Valid @RequestBody ShortlistedCollegesSaveRequest ShortlistedColleges) {

        try {
            return Optional.ofNullable(ShortlistedColleges)
                    .map(mapper::map)
                    .map(shortlistedCollegesService::save)
                    .map(mapper::map)
                    .orElseGet(ShortlistedCollegesResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }

    }

    @PutMapping
    public ShortlistedCollegesResponse update(@RequestBody ShortlistedCollegesUpdateRequest ShortlistedColleges) {
        try {
            return Optional.ofNullable(ShortlistedColleges)
                    .map(mapper::map)
                    .map(shortlistedCollegesService::update)
                    .map(mapper::map)
                    .orElseGet(ShortlistedCollegesResponse::new);
        } catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ShortlistedCollegesResponse delete(@PathVariable("id") long id) {
        try {
            return Optional.of(id)
                    .map(shortlistedCollegesService::delete)
                    .map(mapper::map)
                    .orElseGet(ShortlistedCollegesResponse::new);
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
    public ShortlistedCollegesPaginationResponse retrieveAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "createdTimestamp") String fields,
            @RequestParam(defaultValue = "-1") Integer direction,
            @MatrixVariable Map<String, String> projectionFields) {
        try {
            Collection<String> sortFields = Stream.of(fields.split(",", -1))
                    .collect(Collectors.toList());
            ShortlistedCollegesPaginationRequestDto requestDto = new ShortlistedCollegesPaginationRequestDto();
            requestDto.setPage(page);
            requestDto.setSize(size);
            requestDto.setSortFields(sortFields);
            requestDto.setDirection(direction);
            requestDto.setProjectionFields(projectionFields);
            return Optional.of(requestDto)
                    .map(shortlistedCollegesService::retrieveAll)
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
