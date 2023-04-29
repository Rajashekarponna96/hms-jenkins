package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "student_counsellor_questionnaire")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounsellorQuestionnaireEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_id")
    Long questionnaireId;
    @Column(name = "industry_type")
    String industryType;
    @Column(name = "questionnaire")
    String questionnaire;
    @Column(name = "active")
    @Convert(converter=BooleanToStringConverter.class)
    Boolean active;

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

}
