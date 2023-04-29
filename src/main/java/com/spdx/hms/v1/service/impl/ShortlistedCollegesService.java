package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.ShortlistedCollegesEntity;
import com.spdx.hms.mapper.IShortlistedCollegesDomainMapper;
import com.spdx.hms.repository.IShortlistedCollegesRepository;
import com.spdx.hms.util.HMSConstants;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IShortlistedCollegesService;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.ShortlistedCollegesUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.ShortlistedCollegesResponseDto;
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
public class ShortlistedCollegesService implements IShortlistedCollegesService {

    @Autowired
    private IShortlistedCollegesRepository shortlistedCollegesRepository;

    @Autowired
    private IShortlistedCollegesDomainMapper shortlistedCollegesDomainMapper;


    @Override
    public ShortlistedCollegesResponseDto save(ShortlistedCollegesSaveRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    //.map(this::validate)
                    .map(shortlistedCollegesDomainMapper::map)
                    .map(shortlistedCollegesRepository::save)
                    .map(shortlistedCollegesDomainMapper::map)
                    .orElseGet(ShortlistedCollegesResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public ShortlistedCollegesResponseDto update(ShortlistedCollegesUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(ShortlistedCollegesUpdateRequestDto::getCollegeId)
                    .flatMap(shortlistedCollegesRepository::findById)
                    .map(shortlistedCollegesEntity -> shortlistedCollegesDomainMapper.map(shortlistedCollegesEntity, requestDto))
                    .map(shortlistedCollegesRepository::save)
                    .map(shortlistedCollegesDomainMapper::map)
                    .orElseGet(ShortlistedCollegesResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }


    /*private ShortlistedCollegesSaveRequestDto validate(ShortlistedCollegesSaveRequestDto requestDto) {
        boolean ShortlistedCollegesExist = Optional.ofNullable(requestDto)
                .flatMap(ShortlistedCollegesSaveRequestDto::getCollegeId)
                .map(shortlistedCollegesRepository::findByShortlistedCollegesCode)
                .map(shortlistedCollegesDomainMapper::map)
                .filter(c -> Objects.nonNull(c.getShortlistedCollegesId()))
                .isPresent();
        if (ShortlistedCollegesExist) {
            throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
        }
        return requestDto;
    }*/

    @Override
    public ShortlistedCollegesResponseDto delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(shortlistedCollegesRepository::getById)
                    .flatMap(this::updateValues)
                    .map(shortlistedCollegesRepository::save)
                    .map(shortlistedCollegesDomainMapper::map)
                    .orElseGet(ShortlistedCollegesResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private Optional<ShortlistedCollegesEntity> updateValues(ShortlistedCollegesEntity ShortlistedCollegesEntity) {
        ShortlistedCollegesEntity.setActive(false);
        return Optional.of(ShortlistedCollegesEntity);
    }

    @Override
    public List<ShortlistedCollegesResponseDto> get() {
        try {
            return shortlistedCollegesRepository.findAll().stream()
                    .map(shortlistedCollegesDomainMapper::map).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public ShortlistedCollegesPaginationResponseDto retrieveAll(ShortlistedCollegesPaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(PaginationUtil.getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                .map(p -> shortlistedCollegesRepository.findAll(PaginationUtil.mapProjections(requestDto, HMSConstants.SHORTLISTED_COLLEGE_CRITERIA_FIELDS
                ), p))
                .map(shortlistedCollegesDomainMapper::mapPaginationResponse)
                .orElseGet(ShortlistedCollegesPaginationResponseDto::new);
    }

}
