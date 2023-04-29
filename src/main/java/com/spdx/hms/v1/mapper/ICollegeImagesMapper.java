package com.spdx.hms.v1.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeImagesRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeImagesResponse;
import com.spdx.hms.v1.service.dto.request.CollegeImagesRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeImagesResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        implementationName = "ICollegeImagesMapperImpl")
public interface ICollegeImagesMapper extends IOptionalMapper {

	CollegeImagesRequestDto map(CollegeImagesRequest requestDto);


    CollegeImagesResponse map(CollegeImagesResponseDto responseDto);
}
