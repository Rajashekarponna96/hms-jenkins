package com.spdx.hms.v1.mapper;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.StudentSkillsGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentSkillsSaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentSkillsUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentSkillsResponse;
import com.spdx.hms.v1.service.dto.request.StudentSkillsGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentSkillsUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentSkillsResponseDto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "IStudentSkillsMapperImpl")
public interface IStudentSkillsMapper extends IOptionalMapper {
    StudentSkillsSaveRequestDto map(StudentSkillsSaveRequest requestDto);

    StudentSkillsUpdateRequestDto map(StudentSkillsUpdateRequest requestDto);

    StudentSkillsGetRequestDto map(StudentSkillsGetRequest requestDto);

    StudentSkillsResponse map(StudentSkillsResponseDto responseDto);
}
