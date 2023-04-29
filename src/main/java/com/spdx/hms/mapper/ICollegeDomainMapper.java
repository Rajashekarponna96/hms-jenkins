package com.spdx.hms.mapper;

import com.spdx.hms.entity.CollegeCourseEntity;
import com.spdx.hms.entity.CollegeEntity;
import com.spdx.hms.v1.service.dto.request.CollegeRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeCourseResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegePaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.CollegeResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeDomainMapperImpl")
public interface ICollegeDomainMapper extends IOptionalMapper {

    CollegeEntity map(CollegeRequestDto requestDto);

    CollegeResponseDto map(CollegeEntity requestDto);

    Collection<CollegeCourseResponseDto> mapCollegeCourses(Collection<CollegeCourseEntity> collegeCourse);

    CollegeCourseResponseDto map(CollegeCourseEntity collegeCourseResponseDto);

    @AfterMapping
    default void mapAuditFields(@MappingTarget CollegeEntity collegeEntity, CollegeRequestDto requestDto) {
        collegeEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        collegeEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        //requestDto.getCreatedBy().ifPresent(collegeEntity::setModifiedBy);
    }

    default CollegeEntity map(CollegeEntity collegeEntity, CollegeUpdateRequestDto collegeUpdateRequest) {
        collegeUpdateRequest.getCollegeId().ifPresent(collegeEntity::setCollegeId);
        collegeUpdateRequest.getCollegeCode().ifPresent(collegeEntity::setCollegeCode);
        collegeUpdateRequest.getCollegeName().ifPresent(collegeEntity::setCollegeName);
        collegeUpdateRequest.getShortName().ifPresent(collegeEntity::setShortName);
        collegeUpdateRequest.getEstablishmentYear().ifPresent(collegeEntity::setEstablishmentYear);
        collegeUpdateRequest.getLogo().ifPresent(collegeEntity::setLogo);
        collegeUpdateRequest.getImage().ifPresent(collegeEntity::setImage);
        collegeUpdateRequest.getCollegeLocation().ifPresent(collegeEntity::setCollegeLocation);
        collegeUpdateRequest.getContactNumber().ifPresent(collegeEntity::setContactNumber);
        collegeUpdateRequest.getWebsite().ifPresent(collegeEntity::setWebsite);
        collegeUpdateRequest.getContactPerson().ifPresent(collegeEntity::setContactPerson);
        collegeUpdateRequest.getContactEmail().ifPresent(collegeEntity::setContactEmail);
        collegeUpdateRequest.getAddress1().ifPresent(collegeEntity::setAddress1);
        collegeUpdateRequest.getAddress2().ifPresent(collegeEntity::setAddress2);
        collegeUpdateRequest.getCity().ifPresent(collegeEntity::setCity);
        collegeUpdateRequest.getState().ifPresent(collegeEntity::setState);
        collegeUpdateRequest.getLandMark().ifPresent(collegeEntity::setLandMark);
        collegeUpdateRequest.getManagementInfo().ifPresent(collegeEntity::setManagementInfo);
        collegeUpdateRequest.getGeoLocation().ifPresent(collegeEntity::setGeoLocation);
        collegeUpdateRequest.getGpsLocation().ifPresent(collegeEntity::setGpsLocation);
        collegeUpdateRequest.getLandLineNumber().ifPresent(collegeEntity::setLandLineNumber);
        collegeUpdateRequest.getHostelFacility().ifPresent(collegeEntity::setHostelFacility);
        collegeUpdateRequest.getInstituteType().ifPresent(collegeEntity::setInstituteType);
        collegeUpdateRequest.getHostelFacilities().ifPresent(collegeEntity::setHostelFacilities);
        collegeUpdateRequest.getBankAccountInfo().ifPresent(collegeEntity::setBankAccountInfo);
        
        collegeUpdateRequest.getCreatedTimestamp()
                .ifPresent(collegeEntity::setCreatedTimestamp);
       
        collegeEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        collegeUpdateRequest.getUniversityIds().ifPresent(collegeEntity::setUniversityIds);
        collegeUpdateRequest.getAbout().ifPresent(collegeEntity::setAbout);
        return collegeEntity;
    }

    default CollegePaginationResponseDto mapPaginationResponse(Page<CollegeEntity> p) {
        CollegePaginationResponseDto collegePaginationResponseDto = new CollegePaginationResponseDto();
        collegePaginationResponseDto.setCurrentPage(p.getNumber());
        collegePaginationResponseDto.setTotalItems(p.getTotalElements());
        collegePaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<CollegeResponseDto> collegeResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        collegePaginationResponseDto.setColleges(collegeResponses);
        return collegePaginationResponseDto;
    }
}
