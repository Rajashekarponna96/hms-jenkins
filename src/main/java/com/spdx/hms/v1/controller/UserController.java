package com.spdx.hms.v1.controller;

import static com.spdx.hms.util.RestUtility.APPLICATION_API_SPIDEX_V1_JSON;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spdx.hms.dto.Errors;
import com.spdx.hms.entity.UserEntity;
import com.spdx.hms.exception.CommonServiceException;
import com.spdx.hms.repository.UserRepository;
import com.spdx.hms.security.JwtUtils;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.util.EmailSenderUtil;
import com.spdx.hms.util.PasswordBSEncoder;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.mapper.IUserMapper;
import com.spdx.hms.v1.model.inbound.request.LoginRequest;
import com.spdx.hms.v1.model.inbound.request.OTPRequest;
import com.spdx.hms.v1.model.inbound.request.UserRequest;
import com.spdx.hms.v1.model.inbound.request.UserUpdateRequest;
import com.spdx.hms.v1.model.inbound.response.AuthResponse;
import com.spdx.hms.v1.model.inbound.response.OTPResponse;
import com.spdx.hms.v1.model.inbound.response.UserResponse;
import com.spdx.hms.v1.service.IUserService;
import com.spdx.hms.v1.service.dto.request.UserGetRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;;

@RestController
@Slf4j
@RequestMapping(path="/api/hms/user",consumes = APPLICATION_API_SPIDEX_V1_JSON ,produces=APPLICATION_API_SPIDEX_V1_JSON)
@Api(value = "User Management APIs")
@OpenAPIDefinition(info = @Info(
        title = "User Management APIs",
        description = "User Management APIs"
),tags = {
        @Tag(name = "User", description = "User Management APIs", externalDocs = @ExternalDocumentation(description = "User Management APIs")),
})
public class UserController {
	
	
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordBSEncoder passwordEncoder;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	EmailSenderUtil emailSender;
	
	@Autowired
	IUserMapper userMapper;
	
	

	@GetMapping("/")
	@Operation(tags = {"User"})
	@ApiOperation(value = "fetch all user Info",response = UserResponse.class)
	public List<UserResponse> get() {
		return userService.get()
				.stream()
				.map(userMapper::map)
				.collect(Collectors.toList());
	}

	@PostMapping("/")
	@Operation(tags = {"User"})
	@ApiOperation(value = "Create user", response = UserResponse.class,tags = "user")
	public UserResponse create(@RequestBody UserRequest userRequest) {

		try {
			 return Optional.ofNullable(userRequest)
	                    .map(userMapper::map)
	                    .map(userService::save)
	                    .map(userMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
			
		}catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving user", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
	}
	@PostMapping("/register")
	@Operation(tags = {"User"})
	@ApiOperation(value = "Register user", response = UserResponse.class,tags = "user")
	public UserResponse register(@Valid @RequestBody UserRequest userRequest) {

		try {
			 return Optional.ofNullable(userRequest)
	                    .map(userMapper::map)
	                    .map(userService::save)
	                    .map(userMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
			
		}catch (HMSException hmsEx) {
            throw hmsEx;
        } catch (Exception ex) {
            log.error("Error occurred while saving user", ex);
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
        }
	}

	@PutMapping("/")
	@Operation(tags = {"User"})
	@ApiOperation(value = "Update user",  response = UserResponse.class,tags = "user")
	public UserResponse update(@Valid @RequestBody UserUpdateRequest user) {
		try {
			 return Optional.ofNullable(user)
	                    .map(userMapper::map)
	                    .map(userService::update)
	                    .map(userMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
			
		}catch (HMSException hmsEx) {
           throw hmsEx;
       } catch (Exception ex) {
           log.error("Error occurred while saving user", ex);
           throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
       }
	}

	@DeleteMapping("/{id}")
	@Operation(tags = {"User"})
	@ApiOperation(value = "Delete user",  response = UserResponse.class)
	public UserResponse delete(@PathVariable("id") Long id) {
		try {
			 return Optional.ofNullable(id)
	                    .map(userService::delete)
	                    .map(userMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
			
		}catch (HMSException hmsEx) {
          throw hmsEx;
      } catch (Exception ex) {
          log.error("Error occurred while deleting user", ex);
          throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage(), ex.getMessage(), ex);
      }
	}

	@PostMapping("/login")
	@Operation(tags = {"User"})
	@ApiOperation(value = "login user", response = AuthResponse.class)
	public AuthResponse login(@RequestBody LoginRequest loginRequest) {
	  return userService.login(loginRequest);
	}
	
	@PutMapping("/reset")
	@Operation(tags = {"User"})
	@ApiOperation(value = "Reset  user password",  response = LoginRequest.class)
	public AuthResponse reset(@RequestBody LoginRequest user) {
		UserEntity userEntity = userRepository.findByMobile(user.getUserId());
		if (userEntity != null
				&& userEntity.getPassword().equalsIgnoreCase(passwordEncoder.encode(user.getOldPassword()))) {
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntity.setResetPassword(false);
			userEntity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			userRepository.save(userEntity);
			AuthResponse authResp = new AuthResponse();
			authResp.setRole(userEntity.getRole());
			//authResp.setUserId(userEntity.getUserId());
			authResp.setStatus("Success");
			authResp.setMessage("Reset Password Successfully");
			authResp.setUserName(userEntity.getName());
			//authResp.setWarehouseId(userEntity.getWarehouseIds());
			return authResp;
		} else {
			AuthResponse authResp = new AuthResponse();
			authResp.setUserId(user.getUserId());
			authResp.setStatus("Failure");
			authResp.setMessage("Wrong Old Credentials");
			return authResp;
		}
	}
	
	@PutMapping("/forgot")
	@Operation(tags = {"User"})
	@ApiOperation(value = "Forgot user password",  response = LoginRequest.class)
	public AuthResponse forgot(@RequestBody LoginRequest user) {
		UserEntity userEntity = userRepository.findByUserId(user.getUserId());
		if (userEntity != null ) {
		//	String password = "password";
			String password = CommonsUtil.generateRandomPassword(12);
			userEntity.setPassword(passwordEncoder.encode(password));
			userEntity.setResetPassword(true);
			userEntity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			 userRepository.save(userEntity);
			 try {
					emailSender.sendForgotMessage(userEntity.getEmailId(), password ,userEntity.getEmailId());
					
					AuthResponse authResp = new AuthResponse();
					authResp.setUserId(userEntity.getEmailId());
					authResp.setUserName(userEntity.getName());
					authResp.setRole(userEntity.getRole());
					authResp.setStatus("Success");
					authResp.setMessage("Reset Password Successfully");
					return authResp;
			 } catch (Exception e) {
					log.error("Exception while sending email:{}",CommonsUtil.getErrorStacktrace(e));
			}
			AuthResponse authResp = new AuthResponse();
			authResp.setUserId(userEntity.getEmailId());
			authResp.setStatus("Failure");
			authResp.setMessage("Internal Error");
			return authResp;
		} else {
			AuthResponse authResp = new AuthResponse();
			authResp.setUserId(user.getUserId());
			authResp.setStatus("Failure");
			authResp.setMessage("Wrong Credentials");
			return authResp;
		}
	}
	
	@GetMapping("/otp")
	@Operation(tags = {"User"})
	@ApiOperation(value = "OTP user", response = AuthResponse.class)
	public OTPResponse login(@RequestParam(required = true, value = "mobileNumber") String mobileNumber) {
	  return userService.generateOTP(mobileNumber);
	}
	
	@PostMapping("/otp/verify")
	@Operation(tags = {"User"})
	@ApiOperation(value = "OTP user", response = AuthResponse.class)
	public OTPResponse verify(@RequestBody OTPRequest otpRequest) {
	  return userService.verify(otpRequest);
	}
	
	@PostMapping("/login/otp")
	@Operation(tags = {"User"})
	@ApiOperation(value = "OTP user", response = AuthResponse.class)
	public AuthResponse loginOTP(@RequestBody LoginRequest tokenVari) {
	  return userService.tokenVarify(tokenVari);
	}

	/**
	 * exceptionHandler CommonServiceException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CommonServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		log.error("CommonServiceException :: exception", CommonsUtil.getErrorStacktrace(ex));
		Errors errors = new Errors();
		errors.setErrorType("Errors-Support");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
	}	
	   
	@GetMapping(path = "/email/{emailId}")
	 public UserResponse fetchByEmailId(@PathVariable String emailId) {
	       try {
	            return Optional.ofNullable(emailId)
	                    .map(this::mapEmailId)
	                    .map(userService::retrieve)
	                    .map(userMapper::map)
	                    .orElseGet(UserResponse::new);
	        } catch (HMSException hmsEx) {
	            throw hmsEx;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
	        }
	    }

	   private UserGetRequestDto mapEmailId(String emailId) {
	        return UserGetRequestDto.builder()
	                .emailId(emailId)
	                .build();
	    }
	   
	  @GetMapping(path = "/mobile/{mobileNumber}")
	    public UserResponse fetchByMobileId(@PathVariable String mobileNumber) {
	        try {
	            return Optional.ofNullable(mobileNumber)
	                    .map(this::mapMobileId)
	                    .map(userService::retrieve)
	                    .map(userMapper::map)
	                    .orElseGet(UserResponse::new);
	        } catch (HMSException hmsEx) {
	            throw hmsEx;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0002, ErrorCode.HMS0002.getMessage());
	        }
	    }

	    private UserGetRequestDto mapMobileId(String mobileId) {
	        return UserGetRequestDto.builder()
	                .mobileNumber(mobileId)
	                .build();
	    }

}
