package com.spdx.hms.v1.service.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeCreditsPaginationResponseDto {
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<CollegeCreditsResponseDto> collegeCredits;
}
