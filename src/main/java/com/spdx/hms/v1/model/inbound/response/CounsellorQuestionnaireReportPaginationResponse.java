package com.spdx.hms.v1.model.inbound.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CounsellorQuestionnaireReportPaginationResponse {
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<CounsellorQuestionnaireReportResponse> counsellorQuestionnaireReports;
}
