package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import com.spdx.hms.dto.HostelFacilities;
import com.spdx.hms.dto.BankAccountInfo;
import com.spdx.hms.dto.FeeStructure;
import com.spdx.hms.dto.ManagementInfo;

import java.util.Collection;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@Table(name = "college")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollegeEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long collegeId;

    @Column(name = "college_name")
    private String collegeName;
    
    @Column(name="short_name")
    private String shortName;

    @Column(name = "college_code")
    private String collegeCode;

    @Column(name = "establishment_year")
    private String establishmentYear;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "college_location")
    private String collegeLocation;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "land_line_number")
    private String landLineNumber;

    @Column(name = "institute_type")
    private String instituteType;

    @Column(name = "hostel_facility")
    private String hostelFacility;

    @Column(name = "website")
    private String website;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "address_one")
    private String address1;

    @Column(name = "address_two")
    private String address2;

    @Column(name = "district")
    private String district;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "land_mark")
    private String landMark;
    
    @Column(name = "Geo_location")
    private String geoLocation;
    
    @Convert(converter = GpsLocationConverter.class)
	@Column(name = "Gps_location",columnDefinition = "json")
    private GpsLocation gpsLocation;

    @Convert(converter = BooleanToStringConverter.class)
    @Column(name = "active")
    private Boolean active;

    @Convert(converter = ListToLongConverter.class)
    @Column(name = "university_ids")
    private List<Long> universityIds;
    
     @Convert(converter = ListToHostelFacilities.class)
   	@Column(name = "hostel_facilities",columnDefinition = "json")
   	private List<HostelFacilities> hostelFacilities;

    
    @Convert(converter = ManagementInfoConverter.class)
	@Column(name = "management_info",columnDefinition = "json")
	private ManagementInfo managementInfo;

    @Column(name = "about",length=2000, columnDefinition="TEXT")
    private String about;
    
    @Convert(converter = BankAccountInfoConverter.class)
	@Column(name = "bank_account_info",columnDefinition = "json")
	private BankAccountInfo bankAccountInfo;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "college_id", referencedColumnName = "college_id", insertable = false,
                    updatable = false)
    })
    Collection<CollegeCourseEntity> collegeCourses;
}
