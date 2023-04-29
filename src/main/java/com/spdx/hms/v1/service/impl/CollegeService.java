package com.spdx.hms.v1.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.CollegeCourseEntity;
import com.spdx.hms.entity.CollegeEntity;
import com.spdx.hms.mapper.ICollegeDomainMapper;
import com.spdx.hms.repository.ICollegeRepository;
import com.spdx.hms.util.HMSConstants;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.model.inbound.response.CollegeResponse;
import com.spdx.hms.v1.service.ICollegeService;
import com.spdx.hms.v1.service.dto.request.AbstractPaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegePaginationRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeResponseDto;
import com.spdx.hms.v1.util.PaginationUtil;

@Service
public class CollegeService implements ICollegeService {

    @Autowired
    private ICollegeRepository collegeRepository;

    @Autowired
    private ICollegeDomainMapper collegeDomainMapper;

    /*private Collection<String> entityFieldNames;

    @PostConstruct
    public void init() {
        entityFieldNames = Arrays.stream(CollegeEntity.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }*/

    @Override
    public CollegeResponseDto save(CollegeRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .map(this::validate)
                    .map(collegeDomainMapper::map)
                    .map(collegeRepository::save)
                    .map(collegeDomainMapper::map)
                    .orElseGet(CollegeResponseDto::new);
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CollegeResponseDto update(CollegeUpdateRequestDto requestDto) {
        try {
            return Optional.ofNullable(requestDto)
                    .flatMap(CollegeUpdateRequestDto::getCollegeId)
                    .flatMap(collegeRepository::findById)
                    .map(collegeEntity -> collegeDomainMapper.map(collegeEntity, requestDto))
                    .map(collegeRepository::save)
                    .map(collegeDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0016, ErrorCode.HMS0016.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }


    private CollegeRequestDto validate(CollegeRequestDto requestDto) {
        boolean collegeExist = Optional.ofNullable(requestDto)
                .flatMap(CollegeRequestDto::getCollegeCode)
                .map(collegeRepository::findByCollegeCode)
                .map(collegeDomainMapper::map)
                .filter(c -> Objects.nonNull(c.getCollegeId()))
                .isPresent();
        if (collegeExist) {
            throw new HMSException(ErrorCode.HMS0014, ErrorCode.HMS0014.getMessage());
        }
        return requestDto;
    }

    @Override
    public CollegeResponseDto delete(Long id) {
        try {
            return Optional.ofNullable(id)
                    .flatMap(collegeRepository::findById)
                    .flatMap(this::updateValues)
                    .map(collegeRepository::save)
                    .map(collegeDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0016, ErrorCode.HMS0016.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    private Optional<CollegeEntity> updateValues(CollegeEntity collegeEntity) {
        collegeEntity.setActive(false);
        return Optional.of(collegeEntity);
    }

    @Override
    public CollegeResponseDto retrieve(Long id) {
        try {
            return Optional.ofNullable(id)
                    .flatMap(collegeRepository::findById)
                    .map(collegeDomainMapper::map)
                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0016, ErrorCode.HMS0016.getMessage()));
        } catch (HMSException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }

    @Override
    public CollegePaginationResponseDto retrieveAll(CollegePaginationRequestDto requestDto) {
        List<Sort.Order> orders = requestDto.getSortFields()
                .stream()
                .map(f -> new Sort.Order(PaginationUtil.getSortDirection(requestDto.getDirection()), f))
                .collect(Collectors.toList());
        Pageable pagingSort = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
                                             Sort.by(orders));
        return Optional.of(pagingSort)
                .map(p -> collegeRepository.findAll(mapProjections(requestDto, HMSConstants.COLLEGE_CRITERIA_FIELDS),
                                                    p))
                .map(collegeDomainMapper::mapPaginationResponse)
                .orElseGet(CollegePaginationResponseDto::new);
    }

    public Specification<CollegeEntity> mapProjections(
            CollegePaginationRequestDto requestDto,
            Collection<String> fieldNames) {
        return (root, query, cb) -> cb.and(mapProjections(requestDto, root, cb, fieldNames));
    }

    private Predicate[] mapProjections(
            CollegePaginationRequestDto requestDto,
            Root<CollegeEntity> root,
            CriteriaBuilder cb,
            Collection<String> fieldNames) {
        CollectionJoin<CollegeEntity, Collection<CollegeCourseEntity>> collegeCourseEntities = root.joinCollection(
                "collegeCourses", JoinType.LEFT);
        Collection<Predicate> predicates = Optional.of(requestDto)
                .map(AbstractPaginationRequestDto::getProjectionFields)
                .map(Map::entrySet)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .filter(e -> !e.getKey().startsWith("course."))
                .map(entry -> Optional.of(entry)
                        .filter(e -> fieldNames.contains(e.getKey()))
                        .orElseThrow(() -> new HMSException(ErrorCode.HMS0013, ErrorCode.HMS0013.getMessage())))
                .map(f -> cb.equal(root.<String>get(f.getKey()), f.getValue()))
                .collect(Collectors.toList());
        Collection<Predicate> coursePredicates = Optional.of(requestDto)
                .map(AbstractPaginationRequestDto::getProjectionFields)
                .map(Map::entrySet)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .filter(e -> e.getKey().startsWith("course."))
                .map(f -> cb.equal(collegeCourseEntities.<String>get(f.getKey().replace("course.", "")), f.getValue()))
                .collect(Collectors.toList());
        return Stream.of(predicates, coursePredicates)
                .flatMap(Collection::stream)
                .toArray(Predicate[]::new);
    }

	@Override
	public List<CollegeResponseDto> retrieveList(List<Long> ids) {
		List<CollegeResponse> college=new ArrayList<CollegeResponse>();

			return collegeRepository.findByCollegeId(ids)
			.stream()
            .map(collegeDomainMapper::map)
            .collect(Collectors.toList())
            ;

	
		
	}



}
