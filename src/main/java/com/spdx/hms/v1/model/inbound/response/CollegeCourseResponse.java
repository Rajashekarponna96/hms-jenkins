package com.spdx.hms.v1.model.inbound.response;

import com.spdx.hms.dto.CollegeCourseBranches;
import com.spdx.hms.dto.FeeStructure;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class CollegeCourseResponse {

    private Long collegeCourseId;

    private Long collegeUniversityId;

    private Long collegeId;

    private Long courseId;

    private String courseName;

    private Long allottedSeats;

    private Long fees;

    private List<FeeStructure> feeStructure;

    private Boolean active;

    private Long createdTime;

    private Long modifiedTime;

    private String createdBy;

    private String modifiedBy;
    
    private String about;
    
    private List<CollegeCourseBranches> branches;

}
