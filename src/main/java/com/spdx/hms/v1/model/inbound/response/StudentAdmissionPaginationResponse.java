package com.spdx.hms.v1.model.inbound.response;

import java.util.Collection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionPaginationResponse {
	Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<StudentAdmissionResponse> studentAdmission;

}
