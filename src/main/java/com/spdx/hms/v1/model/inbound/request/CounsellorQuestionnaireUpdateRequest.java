package com.spdx.hms.v1.model.inbound.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounsellorQuestionnaireUpdateRequest {
    Long questionnaireId;
    String industryType;
    String questionnaire;
    Boolean active;
    String modifiedBy;
    Long modifiedTimestamp;

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

    public Optional<String> getModifiedBy() {
        return Optional.ofNullable(modifiedBy);
    }

    public Optional<Long> getModifiedTimestamp() {
        return Optional.ofNullable(modifiedTimestamp);
    }
}
