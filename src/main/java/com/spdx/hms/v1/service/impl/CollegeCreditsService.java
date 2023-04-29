package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.CollegeCourseEntity;
import com.spdx.hms.entity.CollegeCreditsEntity;
import com.spdx.hms.mapper.ICollegeCreditsDomainMapper;
import com.spdx.hms.repository.ICollegeCreditsRepository;
import com.spdx.hms.util.HMSConstants;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICollegeCreditsService;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeCreditsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeCreditsResponseDto;
import com.spdx.hms.v1.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollegeCreditsService implements ICollegeCreditsService {

    @Autowired
    private ICollegeCreditsRepository collegeCreditsRepository;

    @Autowired
    private ICollegeCreditsDomainMapper collegeCreditsDomainMapper;


    @Override
    public CollegeCreditsResponseDto save(CollegeCreditsSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::validate)
                    .map(collegeCreditsDomainMapper::map)
                    .map(collegeCreditsRepository::save)
                    .map(collegeCreditsDomainMapper::map)
                    .orElseGet(CollegeCreditsResponseDto::new);
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CollegeCreditsResponseDto update(CollegeCreditsUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CollegeCreditsUpdateRequestDto::getCollegeId)
                    .flatMap(collegeCreditsRepository::findById)
                    .map(CollegeCreditsEntity -> collegeCreditsDomainMapper.map(CollegeCreditsEntity, requestDto))
                    .map(collegeCreditsRepository::save)
                    .map(collegeCreditsDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0020, ErrorCode.HMS0020.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }


    private CollegeCreditsSaveRequestDto validate(CollegeCreditsSaveRequestDto requestDto) {
        boolean CollegeCreditsExist = Optional.ofNullable(requestDto)
                .flatMap(CollegeCreditsSaveRequestDto::getCollegeId)
                .map(collegeCreditsRepository::findByCollegeId)
                .map(collegeCreditsDomainMapper::map)
                .filter(c -> Objects.nonNull(c.getCollegeId()))
                .isPresent();
        if (CollegeCreditsExist) {
            throw new HMSException(ErrorCode.HMS0019, ErrorCode.HMS0019.getMessage());
        }
        return requestDto;
    }

    @Override
    public CollegeCreditsResponseDto delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(collegeCreditsRepository::getById)
                    .flatMap(this::updateValues)
                    .map(collegeCreditsRepository::save)
                    .map(collegeCreditsDomainMapper::map)
                    .orElseGet(CollegeCreditsResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private Optional<CollegeCreditsEntity> updateValues(CollegeCreditsEntity CollegeCreditsEntity) {
        CollegeCreditsEntity.setActive(false);
        return Optional.of(CollegeCreditsEntity);
    }

    @Override
    public CollegeCreditsResponseDto get(Long id) {
        try {
            return Optional.ofNullable(id)
                    .flatMap(collegeCreditsRepository::findById)
                    .map(collegeCreditsDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0020, ErrorCode.HMS0020.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CollegeCreditsPaginationResponseDto retrieveAll(CollegeCreditsPaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(PaginationUtil.getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                .map(p -> collegeCreditsRepository.findAll(PaginationUtil.mapProjections(requestDto, HMSConstants.COLLEGE_CREDIT_CRITERIA_FIELDS), p))
                .map(collegeCreditsDomainMapper::mapPaginationResponse)
                .orElseGet(CollegeCreditsPaginationResponseDto::new);
    }

}
