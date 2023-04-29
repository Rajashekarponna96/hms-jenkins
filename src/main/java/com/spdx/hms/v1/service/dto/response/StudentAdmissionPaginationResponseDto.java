package com.spdx.hms.v1.service.dto.response;

import java.util.Collection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionPaginationResponseDto {
	Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<StudentAdmissionResponseDto> studentAdmission;

}
