package com.spdx.hms.mapper;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentExperienceEntity;
import com.spdx.hms.v1.model.inbound.response.StudentExperienceResponse;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentExperienceResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentDomainMapperImpl")
public interface IStudentDomainMapper extends IOptionalMapper {
    StudentEntity map(StudentSaveRequestDto requestDto);
    @AfterMapping
    default void mapAuditFields(@MappingTarget StudentEntity studentEntity, StudentSaveRequestDto requestDto) {
        studentEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        requestDto.getCreatedBy().ifPresent(studentEntity::setModifiedBy);
    }

    StudentResponseDto map(StudentEntity requestDto);

    default StudentEntity map(StudentEntity studentEntity, StudentUpdateRequestDto studentUpdateRequestDto) {
        studentUpdateRequestDto.getName()
            .ifPresent(studentEntity::setName);
        studentUpdateRequestDto.getFirstName()
            .ifPresent(studentEntity::setFirstName);
        studentUpdateRequestDto.getLastName()
            .ifPresent(studentEntity::setLastName);
        studentUpdateRequestDto.getFatherName()
        .ifPresent(studentEntity::setFatherName);
        studentUpdateRequestDto.getMotherName()
        .ifPresent(studentEntity::setFatherName);
        studentUpdateRequestDto.getGuardianName()
        .ifPresent(studentEntity::setGuardianName);
        studentUpdateRequestDto.getEmailId()
                .ifPresent(studentEntity::setEmailId);
        studentUpdateRequestDto.getMobileNumber()
                .ifPresent(studentEntity::setMobileNumber);
        studentUpdateRequestDto.getAlternateMobileNumber()
                .ifPresent(studentEntity::setAlternateMobileNumber);

        studentUpdateRequestDto.getAddressOne()
            .ifPresent(studentEntity::setAddressOne);
        studentUpdateRequestDto.getAddressTwo()
            .ifPresent(studentEntity::setAddressTwo);
        studentUpdateRequestDto.getDistrict()
            .ifPresent(studentEntity::setDistrict);
        studentUpdateRequestDto.getCity()
            .ifPresent(studentEntity::setCity);
        studentUpdateRequestDto.getState()
            .ifPresent(studentEntity::setState);
        studentUpdateRequestDto.getZipCode()
            .ifPresent(studentEntity::setZipCode);
        studentUpdateRequestDto.getLandMark()
            .ifPresent(studentEntity::setLandMark);
        studentUpdateRequestDto.getDob()
            .ifPresent(studentEntity::setDob);
        studentUpdateRequestDto.getMaritalStatus()
            .ifPresent(studentEntity::setMaritalStatus);
        studentUpdateRequestDto.getGender()
            .ifPresent(studentEntity::setGender);
        studentUpdateRequestDto.getPhysicallyChallenged()
            .ifPresent(studentEntity::setPhysicallyChallenged);

        studentUpdateRequestDto.getModifiedBy()
                .ifPresent(studentEntity::setModifiedBy);
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        return studentEntity;
    }
    default StudentPaginationResponseDto mapPaginationResponse(Page<StudentEntity> p) {
        StudentPaginationResponseDto studentPaginationResponseDto = new StudentPaginationResponseDto();
        studentPaginationResponseDto.setCurrentPage(p.getNumber());
        studentPaginationResponseDto.setTotalItems(p.getTotalElements());
        studentPaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<StudentResponseDto> studentResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        studentPaginationResponseDto.setStudents(studentResponses);
        return studentPaginationResponseDto;
    }
    @Mapping(target = "student", ignore = true)
    StudentExperienceResponseDto map(StudentExperienceEntity responseDto);

    Collection<StudentExperienceResponseDto> mapExperiences(Collection<StudentExperienceEntity> responseDto);


}
