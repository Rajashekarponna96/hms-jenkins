package com.spdx.hms.v1.util;

import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.dto.request.AbstractPaginationRequestDto;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@UtilityClass
public class PaginationUtil {
    public Sort.Direction getSortDirection(Integer direction) {
        if (direction > 0) {
            return Sort.Direction.ASC;
        } else if (direction < 0) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


    public <T> Specification<T> mapProjections(
            AbstractPaginationRequestDto requestDto,
            Collection<String> fieldNames) {
        return (root, query, cb) -> cb.and(mapProjections(requestDto, root, cb, fieldNames));
    }

    private <T> Predicate[] mapProjections(
            AbstractPaginationRequestDto requestDto,
            Root<T> root,
            CriteriaBuilder cb,
            Collection<String> fieldNames) {
        return Optional.ofNullable(requestDto)
                .map(AbstractPaginationRequestDto::getProjectionFields)
                .map(Map::entrySet)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .filter(entryNotBlankPredicate())
                .map(entry -> Optional.of(entry)
                        .filter(e -> fieldNames.contains(e.getKey()))
                        .orElseThrow(() -> new HMSException(ErrorCode.HMS0013, ErrorCode.HMS0013.getMessage())))
                .map(f -> cb.equal(root.<String>get(f.getKey()), f.getValue()))
                .toArray(Predicate[]::new);
    }

    private java.util.function.Predicate<Map.Entry<String, String>> entryNotBlankPredicate() {
        return entry -> StringUtils.isNotBlank(entry.getKey()) &&
                StringUtils.isNotBlank(entry.getValue());
    }
}
