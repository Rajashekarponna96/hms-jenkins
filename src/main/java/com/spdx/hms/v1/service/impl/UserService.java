package com.spdx.hms.v1.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.spdx.hms.entity.UserEntity;
import com.spdx.hms.entity.UserTokenEntity;
import com.spdx.hms.mapper.IUserDomainMapper;
import com.spdx.hms.repository.IUserTokenRepository;
import com.spdx.hms.repository.UserRepository;
import com.spdx.hms.security.JwtUtils;
import com.spdx.hms.service.SmsService;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.util.EmailSenderUtil;
import com.spdx.hms.util.PasswordBSEncoder;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.model.inbound.request.LoginRequest;
import com.spdx.hms.v1.model.inbound.request.OTPRequest;
import com.spdx.hms.v1.model.inbound.response.AuthResponse;
import com.spdx.hms.v1.model.inbound.response.OTPResponse;
import com.spdx.hms.v1.model.inbound.response.UserResponse;
import com.spdx.hms.v1.service.IUserService;
import com.spdx.hms.v1.service.dto.request.UserRequestDto;
import com.spdx.hms.v1.service.dto.request.UserUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.CounsellorUpdateRequestDto;
import com.spdx.hms.v1.service.dto.request.UserGetRequestDto;
import com.spdx.hms.v1.service.dto.response.CounsellorResponseDto;
import com.spdx.hms.v1.service.dto.response.UserResponseDto;
import com.spdx.hms.v1.util.UserTypeCode;

import software.amazon.awssdk.utils.StringUtils;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IUserTokenRepository userTokenRepository;

	@Autowired
	private IUserDomainMapper userDomainMapper;

	@Autowired
	private PasswordBSEncoder passwordEncoder;

	@Autowired
	EmailSenderUtil emailSender;
	
	@Autowired
	SmsService smsService;

	@Autowired
	JwtUtils jwtUtils;
	
	@Value("${user.token.expiration}")
	private Long expirationInSeconds;

	@Override
	public AuthResponse login(LoginRequest loginRequest) {
		UserEntity userEntity = checkEmailOrMobile(loginRequest);
		if (userEntity != null
				&& userEntity.getPassword().equalsIgnoreCase(passwordEncoder.encode(loginRequest.getPassword()))) {
			String token = jwtUtils.generateJwtToken(loginRequest.getUserId());
			AuthResponse authResp = new AuthResponse();
			authResp.setRole(userEntity.getRole());
			authResp.setUserId(loginRequest.getUserId());
			authResp.setStatus("Success");
			authResp.setMessage("Login Successfully");
			authResp.setUserName(userEntity.getName());
			authResp.setResetPassword(userEntity.isResetPassword());
			authResp.setToken(token);
			return authResp;
		} else {
			AuthResponse authResp = new AuthResponse();
			authResp.setUserId(loginRequest.getUserId());
			authResp.setStatus("Failure");
			authResp.setMessage("Wrong Credentials");
			return authResp;
		}
	}

	private UserEntity checkEmailOrMobile(LoginRequest loginDto) {
		UserEntity userEntity = null;
		if (!StringUtils.isEmpty(loginDto.getUserId()) && loginDto.getUserId().contains("@")) {
			userEntity = userRepository.findByEmailId(loginDto.getUserId());
		} else if (!StringUtils.isEmpty(loginDto.getUserId()) && loginDto.getUserId().length() == 10) {
			userEntity = userRepository.findByMobile(loginDto.getUserId());
		}
		return userEntity;
	}

	@Override
	public UserResponseDto save(UserRequestDto userRequest) {
		try {
			return Optional.ofNullable(userRequest)
					.flatMap(this::validateLoginToken)
					.map(userDomainMapper::map)
					.flatMap(this::generate)
					.map(userRepository::save)
					.map(userDomainMapper::map)
					.orElseGet(UserResponseDto::new);
		} catch(HMSException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}
	
	private Optional<UserRequestDto> validateLoginToken(UserRequestDto userRequest){
		if(userRequest.getUserType()!=null && userRequest.getUserType().equalsIgnoreCase(UserTypeCode.HMSUSTYP0001.getCode())) {
			UserTokenEntity utEntity= userTokenRepository.findByTokenValid(UUID.fromString(userRequest.getTokenId()),userRequest.getMobileNumber());
			if(utEntity ==null) {
				throw new HMSException(ErrorCode.HMS0022, ErrorCode.HMS0022.getMessage());
			}
			
			
			//return Optional.ofNullable(userRequest); 
		}
		UserEntity userEntity = userRepository.findByEmailId(userRequest.getEmailId());
		if (userEntity != null) {
			 throw new HMSException(ErrorCode.HMS0004, ErrorCode.HMS0004.getMessage());
		}
		return Optional.ofNullable(userRequest);
	}

	private Optional<UserEntity> generate(UserEntity user) {
		/*UserEntity userEntity = userRepository.findByEmailId(user.getEmailId());
		if (userEntity != null) {
			 throw new HMSException(ErrorCode.HMS0004, ErrorCode.HMS0004.getMessage());
		} else {*/
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			user.setResetPassword(false);
			user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			return Optional.ofNullable(user);
		//}
	}

	@Override
	public UserResponseDto update(UserUpdateRequestDto userRequest) {
//		try {
//			return Optional.ofNullable(userRequest)
//					.map(userDomainMapper::map)
//					.flatMap(this::generate)
//					.map(userRepository::save)
//					.map(userDomainMapper::map)
//					.orElseGet(UserResponseDto::new);
//		} catch (Exception ex) {
//			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
//		}
		
		try {
            return Optional.ofNullable(userRequest)
            		
                    .flatMap(UserUpdateRequestDto::getId)
                    .flatMap(userRepository::findById)
                    .map(UserEntity -> userDomainMapper.map(UserEntity,userRequest))
                    .map(userRepository::save)
                    .map(userDomainMapper::map)
                    .orElseGet(UserResponseDto::new);
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
            
	}
	
	

	@Override
	public UserResponseDto delete(Long id) {
		try {
			return Optional.ofNullable(id)
					.map(userRepository::getById)
					.flatMap(this::updateValues)
					.map(userRepository::save)
					.map(userDomainMapper::map)
					.orElseGet(UserResponseDto::new);
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	private Optional<UserEntity> updateValues(UserEntity entity) {
		entity.setActive(false);
		return Optional.ofNullable(entity);
	}

	@Override
	public List<UserResponseDto> get() {
		try {
			return userRepository.findAll().stream().map(userDomainMapper::map).collect(Collectors.toList());
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	@Override
	public OTPResponse generateOTP(String mobileNumber) {
		try {
			return Optional.ofNullable(mobileNumber)
					.map(this::mapToken)
					.map(userTokenRepository::save)
					.map(userDomainMapper::map)
					.flatMap(t ->{
						t.setStatus("SUCCESS");
						return Optional.of(t);
					})
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}
	
	
	private UserTokenEntity mapToken(String mobileNumber) {
		UserTokenEntity entity=new UserTokenEntity();
		String otp=CommonsUtil.generateOTP(4);
		entity.setMobileNumber(mobileNumber);
		entity.setMobileToken(otp);
		entity.setExpireTimeSeconds(expirationInSeconds  );
		entity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		entity.setActive(true);
		sendSMS(mobileNumber,otp);
		return entity;
	}
	
	@Async
	private void sendSMS(String mobileNumber,String token) {
		smsService.generateOTPSms(mobileNumber, token);
	}

	@Override
	public OTPResponse verify(OTPRequest request) {
		try {
			return Optional.ofNullable(request)
					.map(this::verifyToken)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}
	
	private OTPResponse verifyToken(OTPRequest request) {
		OTPResponse otpResponse=new OTPResponse();
		otpResponse.setStatus("Failure");
		UserTokenEntity entity = userTokenRepository.findByToken(UUID.fromString(request.getTokenId()),
				request.getOtp(),request.getMobileNumber());
		Long seconds = CommonsUtil.differenceSeconds(entity.getCreatedTime().toLocalDateTime(), LocalDateTime.now());
		if (entity.getExpireTimeSeconds() >= seconds) {
			otpResponse.setStatus("Success");
		}
		otpResponse.setMobileNumber(request.getMobileNumber());
		updateTokenVerification(request,otpResponse.getStatus());
		return otpResponse;

	}
	
	@Override
	public AuthResponse tokenVarify(LoginRequest tokenVari) {
		try {
			return Optional.ofNullable(tokenVari)
					.map(this::verifyTokenAndMob)
					.orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
		} catch (Exception ex) {
			throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
		}
	}

	private AuthResponse verifyTokenAndMob(LoginRequest request) {
		
		UserTokenEntity entity = userTokenRepository.findByTokenMobileValid(request.getUserId(),UUID.fromString(request.getPassword()));
		if(entity!=null) {

		final long Token_VALID_DURATION = 2 * 60;   // 2 minutes

		Long secondss = CommonsUtil.differenceSeconds(entity.getCreatedTime().toLocalDateTime(), LocalDateTime.now());
		
		String token = jwtUtils.generateJwtToken(request.getPassword());

		if (Token_VALID_DURATION >= secondss) {			
			
			AuthResponse otpResponse=new AuthResponse();
			otpResponse.setResetPassword(false);
			otpResponse.setUserId(request.getUserId());
			otpResponse.setStatus("Success");
			otpResponse.setMessage("TokenId Successfully Verified");
			otpResponse.setToken(token);
			return otpResponse;
		}else {
			AuthResponse otpResponse=new AuthResponse();
			otpResponse.setResetPassword(false);
			otpResponse.setUserId(request.getUserId());
		    otpResponse.setStatus("Failure");
			otpResponse.setMessage("TokenId TimeOut");
		    otpResponse.setToken(null);
		    return otpResponse;
		}
		
		}else {	
			AuthResponse otpResponse=new AuthResponse();
			otpResponse.setResetPassword(false);
			otpResponse.setUserId(request.getUserId());
		    otpResponse.setStatus("Failure");
			otpResponse.setMessage("Wrong Credentials");
		    otpResponse.setToken(null);
			return otpResponse;
		}
	}

	
	private void updateTokenVerification(OTPRequest request,String status) {
		UserTokenEntity entity = userTokenRepository.getById(UUID.fromString(request.getTokenId()));
		entity.setActive(false);
		entity.setVerficationStatus(status);
		entity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		userTokenRepository.save(entity);
	}

	@Override
	public UserResponseDto retrieve(UserGetRequestDto userRequest) {
		   try {
	            return Optional.ofNullable(userRequest)
	                    .map(this::fetchByCriteria)
	                    .map(userDomainMapper::map)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0005, ErrorCode.HMS0005.getMessage()));
	        } catch (HMSException ex) {
	            throw ex;
	        } catch (Exception ex) {
	            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
	        }
	}
	
	   private UserEntity fetchByCriteria(UserGetRequestDto requestDto) {
	        if (requestDto.getId().filter(id -> id > 0).isPresent()) {
	            return userRepository.findById(requestDto.getId().orElse(0L))
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
	        } else if (requestDto.getEmailId().filter(StringUtils::isNotBlank).isPresent()) {
	            return requestDto.getEmailId()
	                    .map(userRepository::findByEmailId)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
	        }
	        else if (requestDto.getMobileNumber().filter(StringUtils::isNotBlank).isPresent()) {
	            return requestDto.getMobileNumber()
	                    .map(userRepository::findByMobile)
	                    .orElseThrow(() -> new HMSException(ErrorCode.HMS0006, ErrorCode.HMS0006.getMessage()));
	        }

	        throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
	    }

}
