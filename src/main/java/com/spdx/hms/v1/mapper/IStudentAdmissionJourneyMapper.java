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
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionJourneyGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionJourneySaveRequest;
import com.spdx.hms.v1.model.inbound.request.StudentAdmissionJourneyUpdateRequest;
import com.spdx.hms.v1.model.inbound.request.StudentReportingInfoGetRequest;
import com.spdx.hms.v1.model.inbound.request.StudentReportingInfoUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.StudentAdmissionJourneyResponse;
import com.spdx.hms.v1.model.inbound.response.StudentReportingInfoResponse;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneySaveRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentAdmissionJourneyUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoGetRequestDto;
import com.spdx.hms.v1.service.dto.request.StudentReportingInfoUpdateRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentAdmissionJourneyResponseDto;
import com.spdx.hms.v1.service.dto.response.StudentReportingInfoResponseDto;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.ERROR,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
implementationName = "IStudentAdmissionJourneyMapperImpl")
public interface IStudentAdmissionJourneyMapper extends IOptionalMapper {

	@Mappings({
        @Mapping(target = "createdTimestamp", ignore = true),
        @Mapping(target = "modifiedTimestamp", ignore = true),
      
})
		
	StudentAdmissionJourneySaveRequestDto map(StudentAdmissionJourneySaveRequest requestDto);	

	StudentAdmissionJourneyResponse map(StudentAdmissionJourneyResponseDto responseDto); 
	
	StudentAdmissionJourneyUpdateRequestDto map(StudentAdmissionJourneyUpdateRequest requestDto);
	
	StudentAdmissionJourneyGetRequestDto map(StudentAdmissionJourneyGetRequest requestDto);

	   

	
	 default LocalDateTime map(Long timestamp) {
	   return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
	    }

	    default LocalDateTime map(String date) {
	        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
	        return LocalDateTime.parse(date, dateTimeFormatter);
	    }
		
}
