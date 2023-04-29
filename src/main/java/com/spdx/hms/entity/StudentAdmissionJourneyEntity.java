package com.spdx.hms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spdx.hms.dto.AdmissionJourney;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@Entity
@Table(name = "student_admission_journey")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAdmissionJourneyEntity extends AuditableEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "std_profile_id")
    Long stdProfileId;
    
    @Column(name = "admission_journey")
    @Convert(converter=ListToAdmissionJourneyConverter.class)
    List<AdmissionJourney> AdmissionJourney;
   
    @Column(name = "active")
    Boolean active;
}
