package com.spdx.hms.v1.service;

import java.util.List;

import com.spdx.hms.v1.model.inbound.request.LoginRequest;
import com.spdx.hms.v1.model.inbound.request.OTPRequest;
import com.spdx.hms.v1.model.inbound.response.AuthResponse;
import com.spdx.hms.v1.model.inbound.response.OTPResponse;
import com.spdx.hms.v1.model.inbound.response.UserResponse;
import com.spdx.hms.v1.service.dto.request.StudentGetRequestDto;
import com.spdx.hms.v1.service.dto.request.UserRequestDto;
import com.spdx.hms.v1.service.dto.request.UserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.UserGetRequestDto;
import com.spdx.hms.v1.service.dto.response.StudentResponseDto;
import com.spdx.hms.v1.service.dto.response.UserResponseDto;

public interface IUserService {
	
	AuthResponse login(LoginRequest loginRequest);
	
	UserResponseDto save(UserRequestDto userRequest);
	
	UserResponseDto update(UserUpdateRequestDto userRequest);
	
	UserResponseDto delete(Long id);
	
	List<UserResponseDto> get();
	
	OTPResponse generateOTP(String mobileNumber);
	
	OTPResponse verify(OTPRequest request);

	AuthResponse tokenVarify(LoginRequest tokenVari);

	UserResponseDto retrieve(UserGetRequestDto userRequest);

}
