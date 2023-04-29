package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.AppliedCollegesEntity;
import com.spdx.hms.mapper.IAppliedCollegesDomainMapper;
import com.spdx.hms.repository.IAppliedCollegesRepository;
import com.spdx.hms.util.HMSConstants;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IAppliedCollegesService;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.AppliedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.AppliedCollegesResponseDto;
import com.spdx.hms.v1.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppliedCollegesService implements IAppliedCollegesService {

    @Autowired
    private IAppliedCollegesRepository appliedCollegesRepository;

    @Autowired
    private IAppliedCollegesDomainMapper appliedCollegesDomainMapper;


    @Override
    public AppliedCollegesResponseDto save(AppliedCollegesSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    //.map(this::validate)
                    .map(appliedCollegesDomainMapper::map)
                    .map(appliedCollegesRepository::save)
                    .map(appliedCollegesDomainMapper::map)
                    .orElseGet(AppliedCollegesResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public AppliedCollegesResponseDto update(AppliedCollegesUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(AppliedCollegesUpdateRequestDto::getAppId)
                    .flatMap(appliedCollegesRepository::findById)
                    .map(appliedCollegesEntity -> appliedCollegesDomainMapper.map(appliedCollegesEntity, requestDto))
                    .map(appliedCollegesRepository::save)
                    .map(appliedCollegesDomainMapper::map)
                    .orElseGet(AppliedCollegesResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }


    /*private AppliedCollegesSaveRequestDto validate(AppliedCollegesSaveRequestDto requestDto) {
        boolean AppliedCollegesExist = Optional.ofNullable(requestDto)
                .flatMap(AppliedCollegesSaveRequestDto::getCollegeId)
                .map(AppliedCollegesRepository::findByAppliedCollegesCode)
                .map(AppliedCollegesDomainMapper::map)
                .filter(c -> Objects.nonNull(c.getAppliedCollegesId()))
                .isPresent();
        if (AppliedCollegesExist) {
            throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
        }
        return requestDto;
    }*/

    @Override
    public Boolean delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(appliedCollegesRepository::deactivateAppliedCollege)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
    private Optional<AppliedCollegesEntity> updateValues(AppliedCollegesEntity AppliedCollegesEntity) {
        AppliedCollegesEntity.setActive(false);
        return Optional.of(AppliedCollegesEntity);
    }

    @Override
    public List<AppliedCollegesResponseDto> get() {
        try {
            return appliedCollegesRepository.findAll().stream()
                    .map(appliedCollegesDomainMapper::map).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public AppliedCollegesPaginationResponseDto retrieveAll(AppliedCollegesPaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(PaginationUtil.getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                //FIXME
                .map(p -> appliedCollegesRepository.findAll(PaginationUtil.mapProjections(requestDto,HMSConstants.APPLIED_COLLEGE_CRITERIA_FIELDS
                ), p))
                .map(appliedCollegesDomainMapper::mapPaginationResponse)
                .orElseGet(AppliedCollegesPaginationResponseDto::new);
    }

}
