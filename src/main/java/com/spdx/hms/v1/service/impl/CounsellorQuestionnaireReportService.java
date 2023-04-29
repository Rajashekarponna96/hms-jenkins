package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.CounsellorQuestionnaireReportEntity;
import com.spdx.hms.mapper.ICounsellorQuestionnaireReportDomainMapper;
import com.spdx.hms.repository.ICounsellorQuestionnaireReportRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICounsellorQuestionnaireReportService;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireReportUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireReportResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class CounsellorQuestionnaireReportService implements ICounsellorQuestionnaireReportService {

    @Autowired
    private ICounsellorQuestionnaireReportRepository CounsellorQuestionnaireReportRepository;
    @Autowired
    private ICounsellorQuestionnaireReportDomainMapper CounsellorQuestionnaireReportDomainMapper;

    @Override
    public CounsellorQuestionnaireReportResponseDto save(CounsellorQuestionnaireReportSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::checkQuestionnaireAlreadyExists)
                    .map(CounsellorQuestionnaireReportDomainMapper::map)
                    .map(CounsellorQuestionnaireReportRepository::save)
                    .map(CounsellorQuestionnaireReportDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("exception occurred while saving", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CounsellorQuestionnaireReportResponseDto update(CounsellorQuestionnaireReportUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CounsellorQuestionnaireReportUpdateRequestDto::getQtyReportId)
                    .flatMap(CounsellorQuestionnaireReportRepository::findById)
                    .map(CounsellorQuestionnaireReportEntity -> CounsellorQuestionnaireReportDomainMapper.map(
                            CounsellorQuestionnaireReportEntity, requestDto))
                    .map(CounsellorQuestionnaireReportRepository::save)
                    .map(CounsellorQuestionnaireReportDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CounsellorQuestionnaireReportResponseDto retrieve(CounsellorQuestionnaireReportGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::fetchByCriteria)
                    .map(CounsellorQuestionnaireReportDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CounsellorQuestionnaireReportPaginationResponseDto retrieveAll(
            CounsellorQuestionnaireReportPaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                .map(p -> CounsellorQuestionnaireReportRepository.findAll(
                        CounsellorQuestionnaireReportState(requestDto), p))
                .map(CounsellorQuestionnaireReportDomainMapper::mapPaginationResponse)
                .orElseGet(CounsellorQuestionnaireReportPaginationResponseDto::new);
    }


    public Specification<CounsellorQuestionnaireReportEntity> CounsellorQuestionnaireReportState(
            CounsellorQuestionnaireReportPaginationRequestDto requestDto) {
        return (root, query, cb) -> cb.and(Optional.ofNullable(requestDto)
                                                   .map(CounsellorQuestionnaireReportPaginationRequestDto::getProjectionFields)
                                                   .map(Map::entrySet)
                                                   .map(Collection::stream)
                                                   .orElseGet(Stream::empty)
                                                   .filter(entry -> StringUtils.isNotBlank(entry.getKey()) &&
                                                           StringUtils.isNotBlank(entry.getValue()))
                                                   //TODO Need to add criteria field exists check before creating the
                                                   // predicate
                                                   .map(f -> cb.equal(root.<String>get(f.getKey()), f.getValue()))
                                                   .toArray(Predicate[]::new));

    }


    @Override
    public Boolean delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(CounsellorQuestionnaireReportRepository::deactivateCounsellorQuestionnaireReport)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private CounsellorQuestionnaireReportEntity fetchByCriteria(CounsellorQuestionnaireReportGetRequestDto requestDto) {
        if (requestDto.getQuestionnaireId().filter(id -> id > 0).isPresent()) {
            return CounsellorQuestionnaireReportRepository.findById(requestDto.getQuestionnaireId().orElse(0L))
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
        }

        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
    }

    private CounsellorQuestionnaireReportSaveRequestDto checkQuestionnaireAlreadyExists(
            CounsellorQuestionnaireReportSaveRequestDto requestDto) {
        //TODO Need to add condition to checkQuestionnaireAlreadyExists in the database
        return requestDto;
    }

    private Sort.Direction getSortDirection(Integer direction) {
        if (direction > 0) {
            return Sort.Direction.ASC;
        } else if (direction < 0) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
