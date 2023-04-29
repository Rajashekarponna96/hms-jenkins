package com.spdx.hms.v1.mapper;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import com.spdx.hms.mapper.IOptionalMapper;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentContactGetRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentContactSaveRequest;
import com.spdx.hms.v1.model.inbound.request.CollegeStudentContactUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.CollegeStudentContactResponse;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactGetRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactSaveRequestDto;
import com.spdx.hms.v1.service.dto.request.CollegeStudentContactUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeStudentContactResponseDto;
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "ICollegeStudentContactMapperImpl")

public interface ICollegeStudentContactMapper extends IOptionalMapper {
	
	@Mappings({
		@Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})
	
	CollegeStudentContactSaveRequestDto map(CollegeStudentContactSaveRequest requestDto);
	@Mappings({
        @Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
        @Mapping(target = "active", ignore = true)
})
	CollegeStudentContactUpdateRequestDto map(CollegeStudentContactUpdateRequest requestDto);
	CollegeStudentContactGetRequestDto map(CollegeStudentContactGetRequest requestDto);
	CollegeStudentContactResponse map(CollegeStudentContactResponseDto responseDto);
	
	default LocalDateTime map(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
    default LocalDateTime map(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return LocalDateTime.parse(date, dateTimeFormatter);
    }


}
