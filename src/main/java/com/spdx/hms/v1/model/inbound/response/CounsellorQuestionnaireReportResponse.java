package com.spdx.hms.v1.model.inbound.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounsellorQuestionnaireReportResponse {
    Long qtyReportId;
    Long questionnaireId;
    String industryType;
    String questionnaire;
    Boolean active;
    String createdBy;
    String modifiedBy;
    Long createdTimestamp;
    Long modifiedTimestamp;

    public Optional<Long> getQtyReportId() {
        return Optional.ofNullable(qtyReportId);
    }
    public Optional<Long> getQuestionnaireId() {
        return Optional.ofNullable(questionnaireId);
    }

    public Optional<String> getIndustryType() {
        return Optional.ofNullable(industryType);
    }

    public Optional<String> getQuestionnaire() {
        return Optional.ofNullable(questionnaire);
    }

    public Optional<Boolean> getActive() {
        return Optional.ofNullable(active);
    }

    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
    }

    public Optional<Long> getCreatedTimestamp() {
        return Optional.ofNullable(createdTimestamp);
    }

    public Optional<Long> getModifiedTimestamp() {
        return Optional.ofNullable(modifiedTimestamp);
    }
}
