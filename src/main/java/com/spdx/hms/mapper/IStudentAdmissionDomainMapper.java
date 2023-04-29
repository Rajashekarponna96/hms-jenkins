package com.spdx.hms.mapper;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import com.spdx.hms.entity.StudentAdmissionEntity;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionPaginationResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, implementationName = "IStudentAdmissionDomainMapperImpl")
public interface IStudentAdmissionDomainMapper extends IOptionalMapper {

	StudentAdmissionEntity map(StudentAdmissionSaveRequestDto requestDto);

	@AfterMapping
	default void mapAuditFields(@MappingTarget StudentAdmissionEntity studentAdmissionEntity) {
		studentAdmissionEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		studentAdmissionEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	}

	default StudentAdmissionEntity map(StudentAdmissionEntity requestDto, StudentAdmissionUpdateRequestDto request) {
		request.getAdmissionNumber().ifPresent(requestDto::setAdmissionNumber);
		request.getAppId().ifPresent(requestDto::setAppId);
		request.getStudentId().ifPresent(requestDto::setStudentId);
		request.getActive().ifPresent(requestDto::setActive);
		request.getStudentMobileNumber().ifPresent(requestDto::setStudentMobileNumber);
		request.getStudentEmailId().ifPresent(requestDto::setStudentEmailId);
		request.getStudentName().ifPresent(requestDto::setStudentName);
		request.getCourseId().ifPresent(requestDto::setCourseId);
		request.getCreatedBy().ifPresent(requestDto::setCreatedBy);
		request.getBranchName().ifPresent(requestDto::setBranchName);
		request.getAdmittedDateTime().ifPresent(requestDto::setAdmittedDateTime);
		request.getAdmissionStatus().ifPresent(requestDto::setAdmissionStatus);
		request.getCreatedBy().ifPresent(requestDto::setCreatedBy);
		request.getModifiedBy().ifPresent(requestDto::setModifiedBy);
		request.getCreatedTimestamp().ifPresent(requestDto::setCreatedTimestamp);
		request.getModifiedTimestamp().ifPresent(requestDto::setModifiedTimestamp);
		request.getContactId().ifPresent(requestDto::setContactId);
		request.getCollegeCourse().ifPresent(requestDto::setCollegeCourse);
		request.getAppDate().ifPresent(requestDto::setAppDate);

		return requestDto;
	}

	StudentAdmissionEntity map(StudentAdmissionUpdateRequestDto studentAdmissionUpdateRequestDto);

	StudentAdmissionResponseDto map(StudentAdmissionEntity requestDto);

	StudentAdmissionResponseDto map(StudentAdmissionGetRequestDto requestDto);
	
	default StudentAdmissionPaginationResponseDto mapPaginationResponse(Page<StudentAdmissionEntity> p) {
		StudentAdmissionPaginationResponseDto StudentAdmissionPaginationResponseDto = new StudentAdmissionPaginationResponseDto();
		StudentAdmissionPaginationResponseDto.setCurrentPage(p.getNumber());
		StudentAdmissionPaginationResponseDto.setTotalItems(p.getTotalElements());
		StudentAdmissionPaginationResponseDto.setTotalPages(p.getTotalPages());
        Collection<StudentAdmissionResponseDto> StudentAdmissionResponses = Optional.of(p)
                .map(Page::getContent)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(this::map)
                .collect(Collectors.toList());
        StudentAdmissionPaginationResponseDto.setStudentAdmission(StudentAdmissionResponses);
        return StudentAdmissionPaginationResponseDto;
    }


}
