package com.spdx.hms.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.entity.CollegeImagesEntity;
import com.spdx.hms.v1.service.dto.request.CollegeImagesRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeImagesResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeImagesDomainMapperImpl")
public interface ICollegeImagesDomainMapper extends IOptionalMapper {
   
	CollegeImagesEntity map(CollegeImagesRequestDto requestDto);

    CollegeImagesResponseDto map(CollegeImagesEntity requestDto);

    default CollegeImagesEntity map(CollegeImagesEntity studentEntity, CollegeImagesRequestDto studentUpdateRequestDto) {
       
       /* studentUpdateRequestDto.getModifiedBy()
                .ifPresent(studentEntity::setModifiedBy);
        studentEntity.setModifiedTimestamp(Instant.now().toEpochMilli());
        studentUpdateRequestDto.isActive()
                .ifPresent(studentEntity::setActive);*/
        return studentEntity;
    }
}
