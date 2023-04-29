package com.spdx.hms.v1.service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StudentPaginationResponseDto {
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<StudentResponseDto> students;
}
