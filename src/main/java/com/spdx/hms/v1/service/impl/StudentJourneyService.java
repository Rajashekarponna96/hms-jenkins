package com.spdx.hms.v1.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spdx.hms.dto.AdmissionJourney;
import com.spdx.hms.entity.StudentAdmissionJourneyEntity;
import com.spdx.hms.repository.IStudentAdmissionJourneyRepository;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.IStudentJourneyService;

@Service
public class StudentJourneyService implements IStudentJourneyService {
	
	@Autowired
    private IStudentAdmissionJourneyRepository studentAdmRepository;


    @Override
    public void save(Long studentId,String type) {
        try {
             Optional.ofNullable(studentId)
                    .flatMap(this::validate)
                    .map(studentAdmRepository::save);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
    
    private Optional<StudentAdmissionJourneyEntity> validate(Long studentId) {
    	StudentAdmissionJourneyEntity entity = studentAdmRepository.getById(studentId);
    	if(entity == null) {
    		entity =new StudentAdmissionJourneyEntity();
    		entity.setStdProfileId(studentId);
    		entity.setAdmissionJourney(getInitialData());
    	}
    	return Optional.ofNullable(entity);
    }
    
    private List<AdmissionJourney> getInitialData() {
    	String jsonString = null;
		try {
			jsonString = new String(Files.readAllBytes(
					Paths.get(StudentJourneyService.class.getClassLoader().getResource("profile_config.json").toURI())));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		Gson mapper = new Gson();
		return mapper.fromJson(jsonString, new TypeToken<List<AdmissionJourney>>(){}.getType());
    }

   
}
