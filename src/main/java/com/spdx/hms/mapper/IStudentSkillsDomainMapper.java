package com.spdx.hms.mapper;

import com.spdx.hms.entity.StudentEntity;
import com.spdx.hms.entity.StudentSkillsEntity;
import com.spdx.hms.v1.service.dto.request.StudentSkillsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentSkillsResponseDto;
import org.mapstruct.*;

import java.time.Instant;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.WARN, collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, implementationName = "IStudentSkillsDomainMapperImpl")
public interface IStudentSkillsDomainMapper extends IOptionalMapper {
	StudentSkillsEntity map(StudentSkillsSaveRequestDto requestDto);

	@AfterMapping
	default void mapAuditFields(@MappingTarget StudentSkillsEntity studentEntity) {
		studentEntity.setCreatedTimestamp(Instant.now().toEpochMilli());
		studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
	}

	StudentSkillsResponseDto map(StudentSkillsEntity requestDto);

	@Mapping(target = "studentExperiences", ignore = true)
	StudentResponseDto map(StudentEntity responseDto);

	default StudentSkillsEntity map(StudentSkillsEntity studentEntity,
			StudentSkillsUpdateRequestDto studentUpdateRequestDto) {
		
		studentUpdateRequestDto.getStdSkillId().ifPresent(studentEntity::setStdSkillId);

		studentUpdateRequestDto.getStdProfileId().ifPresent(studentEntity::setStdProfileId);
		studentUpdateRequestDto.getTotalExperience().ifPresent(studentEntity::setTotalExperience);
		studentUpdateRequestDto.getCategory().ifPresent(studentEntity::setCategory);
		studentUpdateRequestDto.getActive().ifPresent(studentEntity::setActive);
		
		studentUpdateRequestDto.getLevel().ifPresent(studentEntity::setLevel);
		studentUpdateRequestDto.getSpecialization().ifPresent(studentEntity::setSpecialization);
		return studentEntity;
	}

}
