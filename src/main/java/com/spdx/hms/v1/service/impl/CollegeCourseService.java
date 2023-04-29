package com.spdx.hms.v1.service.impl;

import com.spdx.hms.entity.CollegeCourseEntity;
import com.spdx.hms.mapper.ICollegeCourseDomainMapper;
import com.spdx.hms.repository.ICollegeCourseRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICollegeCourseService;
import com.spdx.hms.v1.service.dto.request.CollegeCourseRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCourseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollegeCourseService implements ICollegeCourseService {

    @Autowired
    private ICollegeCourseRepository collegeCourseRepository;

    @Autowired
    private ICollegeCourseDomainMapper collegeCourseDomainMapper;

    @Override
    public CollegeCourseResponseDto save(CollegeCourseRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(collegeCourseDomainMapper::map)
                    .flatMap(this::validate)
                    .map(collegeCourseRepository::save)
                    .map(collegeCourseDomainMapper::map)
                    .orElseGet(CollegeCourseResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CollegeCourseResponseDto update(CollegeCourseRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(collegeCourseDomainMapper::map)
                    .flatMap(this::validateUpdate)
                    .map(collegeCourseRepository::save)
                    .map(collegeCourseDomainMapper::map)
                    .orElseGet(CollegeCourseResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }


    private Optional<CollegeCourseEntity> validate(CollegeCourseEntity collegecourseEntity) {
        CollegeCourseEntity collegeEntityD = collegeCourseRepository.findByCollegeIdAndCourseId(
                collegecourseEntity.getCollegeId(), collegecourseEntity.getCourseId());
        if (collegeEntityD != null) {
            throw new HMSException(ErrorCode.HMS0017, ErrorCode.HMS0017.getMessage());
        } else {
            return Optional.of(collegecourseEntity);
        }
    }

    private Optional<CollegeCourseEntity> validateUpdate(CollegeCourseEntity collegecourseEntity) {
        CollegeCourseEntity collegeEntityD = collegeCourseRepository.findByCollegeIdAndCourseId(
                collegecourseEntity.getCollegeId(), collegecourseEntity.getCourseId());
        if (collegeEntityD != null && Objects.equals(collegeEntityD.getCollegeCourseId(),
                                                     collegecourseEntity.getCollegeCourseId())) {
            return Optional.of(collegecourseEntity);
        } else {
            throw new HMSException(ErrorCode.HMS0018, ErrorCode.HMS0018.getMessage());
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(collegeCourseRepository::deactivateCollegeCourse)
                    .map(d -> d > 0)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0018, ErrorCode.HMS0018.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CollegeCourseResponseDto retrieve(Long id) {
        try {
            return Optional.ofNullable(id)
                    .flatMap(collegeCourseRepository::findById)
                    .map(collegeCourseDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0017, ErrorCode.HMS0017.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public List<CollegeCourseResponseDto> retrieveByCollee(Long id) {
        try {
            return Optional.ofNullable(id)
                    .map(collegeCourseRepository::findByCollege)
                    .get().stream()
                    .map(collegeCourseDomainMapper::map)
                    .collect(Collectors.toList());
            // .orElseThrow(() -> new HMSException(ErrorCode.HMS0017, ErrorCode.HMS0017.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
}
