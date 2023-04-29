package com.spdx.hms.v1.junit;

import com.spdx.hms.v1.controller.StudentController;
import com.spdx.hms.v1.mapper.IStudentMapper;
import com.spdx.hms.v1.model.inbound.request.StudentSaveRequest;
import com.spdx.hms.v1.model.inbound.response.StudentResponse;
import com.spdx.hms.v1.service.IStudentService;
import com.spdx.hms.v1.service.dto.request.StudentSaveRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class StudentControllerTest {
    @InjectMocks
    StudentController studentController;
    @Mock
    IStudentMapper iStudentMapper;
    @Mock
    IStudentService iStudentService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void expectStudentSuccess() {
        Mockito.when(iStudentMapper.map(Mockito.any(StudentSaveRequest.class)))
                .thenReturn(new StudentSaveRequestDto());
        Mockito.when(iStudentService.save(Mockito.any(StudentSaveRequestDto.class)))
                .thenReturn(new StudentResponseDto());
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setActive(Boolean.TRUE);
        Mockito.when(iStudentMapper.map(Mockito.any(StudentResponseDto.class)))
                .thenReturn(studentResponse);
        StudentResponse response = studentController.create(new StudentSaveRequest());
        Assertions.assertEquals(response.getActive().get(), Boolean.TRUE);
    }
}
