package com.spdx.hms.entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@Table(name = "student_reportinginfo")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentReportingInfoEntity   extends AuditableEntity implements Serializable {
	
		@Id
		@Column(name = "stdAdmission_Id")
	    Long stdAdmissionId;
	    @Column(name = "date_Time")
	    String dateTime;	    
	    @Column(name = "contact_Person")
	    String contactPerson;
	    @Column(name = "contact_PersonNumber")
	    String contactPersonNumber;
	    @Column(name = "documents_Required")
	    String documentsRequired;
	    @Column(name = "comments")
	    String comments;	    
	    @Column(name = "status")
	    String status;
	    @Column(name = "active")
	    @Convert(converter=BooleanToStringConverter.class)
	    Boolean active;
	    
	    
	
	
	

}
