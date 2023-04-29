package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.CounsellorQuestionnaireEntity;
import com.spdx.hms.mapper.ICounsellorQuestionnaireDomainMapper;
import com.spdx.hms.repository.ICounsellorQuestionnaireRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICounsellorQuestionnaireService;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnairePaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorQuestionnaireUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnairePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CounsellorQuestionnaireResponseDto;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class CounsellorQuestionnaireService implements ICounsellorQuestionnaireService {

    @Autowired
    private ICounsellorQuestionnaireRepository CounsellorQuestionnaireRepository;
    @Autowired
    private ICounsellorQuestionnaireDomainMapper CounsellorQuestionnaireDomainMapper;

    @Override
    public CounsellorQuestionnaireResponseDto save(CounsellorQuestionnaireSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::checkQuestionnaireAlreadyExists)
                    .map(CounsellorQuestionnaireDomainMapper::map)
                    .map(CounsellorQuestionnaireRepository::save)
                    .map(CounsellorQuestionnaireDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("exception occurred while saving", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CounsellorQuestionnaireResponseDto update(CounsellorQuestionnaireUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CounsellorQuestionnaireUpdateRequestDto::getQuestionnaireId)
                    .flatMap(CounsellorQuestionnaireRepository::findById)
                    .map(CounsellorQuestionnaireEntity -> CounsellorQuestionnaireDomainMapper.map(CounsellorQuestionnaireEntity, requestDto))
                    .map(CounsellorQuestionnaireRepository::save)
                    .map(CounsellorQuestionnaireDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CounsellorQuestionnaireResponseDto retrieve(CounsellorQuestionnaireGetRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::fetchByCriteria)
                    .map(CounsellorQuestionnaireDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CounsellorQuestionnairePaginationResponseDto retrieveAll(CounsellorQuestionnairePaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                .map(p -> CounsellorQuestionnaireRepository.findAll(CounsellorQuestionnaireState(requestDto), p))
                .map(CounsellorQuestionnaireDomainMapper::mapPaginationResponse)
                .orElseGet(CounsellorQuestionnairePaginationResponseDto::new);
    }


    public Specification<CounsellorQuestionnaireEntity> CounsellorQuestionnaireState(CounsellorQuestionnairePaginationRequestDto requestDto) {
        return (root, query, cb) -> cb.and(Optional.ofNullable(requestDto)
                                                   .map(CounsellorQuestionnairePaginationRequestDto::getProjectionFields)
                                                   .map(Map::entrySet)
                                                   .map(Collection::stream)
                                                   .orElseGet(Stream::empty)
                                                   .filter(entry -> StringUtils.isNotBlank(entry.getKey()) &&
                                                           StringUtils.isNotBlank(entry.getValue()))
                                                   //TODO Need to add criteria field exists check before creating the predicate
                                                   .map(f -> cb.equal(root.<String>get(f.getKey()), f.getValue()))
                                                   .toArray(Predicate[]::new));

    }


    @Override
    public Boolean delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(CounsellorQuestionnaireRepository::deactivateCounsellorQuestionnaire)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private CounsellorQuestionnaireEntity fetchByCriteria(CounsellorQuestionnaireGetRequestDto requestDto) {
        if (requestDto.getQuestionnaireId().filter(id -> id > 0).isPresent()) {
            return CounsellorQuestionnaireRepository.findById(requestDto.getQuestionnaireId().orElse(0L))
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
        } 

        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
    }

    private CounsellorQuestionnaireSaveRequestDto checkQuestionnaireAlreadyExists(CounsellorQuestionnaireSaveRequestDto requestDto) {
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
