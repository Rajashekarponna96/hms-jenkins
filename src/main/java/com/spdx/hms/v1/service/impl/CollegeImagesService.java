package com.spdx.hms.v1.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.spdx.hms.entity.CollegeImagesEntity;
import com.spdx.hms.mapper.ICollegeImagesDomainMapper;
import com.spdx.hms.repository.ICollegeImagesRepository;
import com.spdx.hms.service.S3BucketService;
import com.spdx.hms.util.CommonsUtil;
import com.spdx.hms.v1.exception.ErrorCode;
import com.spdx.hms.v1.exception.HMSException;
import com.spdx.hms.v1.service.ICollegeImagesService;
import com.spdx.hms.v1.service.dto.request.CollegeImagesRequestDto;
import com.spdx.hms.v1.service.dto.response.CollegeImagesResponseDto;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j
public class CollegeImagesService implements ICollegeImagesService {
	
	@Autowired
    private ICollegeImagesRepository collegeImagesRepository;

    @Autowired
    private ICollegeImagesDomainMapper collegeImagesDomainMapper;
    
    @Autowired
    private S3BucketService s3BucketService;
    
    @Value("${bucketName}")
    private String bucketName;
    
    private  final AmazonS3 s3;

    public CollegeImagesService(AmazonS3 s3) {
        this.s3 = s3;
    }
    
    @Value("${aws.images.url}")
    private String imageUrl;

    @Override
    public List<CollegeImagesResponseDto> save(CollegeImagesRequestDto requestDto) {
        try {
            return images(requestDto,null);
                   
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
    }
    
    @Override
	public List<CollegeImagesResponseDto> save(CollegeImagesRequestDto requestDto, List<MultipartFile> files) {
    	 try {
             return images(requestDto,files);
                    
         } catch (Exception ex) {
             throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
         }
	}
    
    private List<CollegeImagesResponseDto> images(CollegeImagesRequestDto requestDto ,List<MultipartFile> files) {
    	List<CollegeImagesResponseDto> collegeImagesResponseDtos=new ArrayList<>();
       if(files.size()<=10) {
    	for(MultipartFile file:files) {
    		if(uploadFile(file)) {
    			requestDto.setImageUrls(imageUrl+file.getOriginalFilename());
    			Optional<CollegeImagesResponseDto> coll= Optional.ofNullable(requestDto)
    			.map(collegeImagesDomainMapper::map)
                .map(collegeImagesRepository::save)
                .map(collegeImagesDomainMapper::map);
    			collegeImagesResponseDtos.add(coll.get());
    		}
    	}}else {
            log.error("Please Select Upto Only 10 Files");
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
    	}

    	return collegeImagesResponseDtos;
    	
    }
    
    public boolean uploadFile(MultipartFile file) {
		
	//	S3Client s3Client = s3BucketService.getS3BucketConnection();
		String originalFilename = file.getOriginalFilename();
		 try {
//			s3Client.putObject(PutObjectRequest.builder()
//			        .bucket(bucketName)
//			        .key(file.getOriginalFilename())
//			        .build(),
//			        RequestBody.fromBytes(file.getBytes()));
			   File file1 = convertMultiPartToFile(file);
               PutObjectResult putObjectResult = s3.putObject(bucketName, originalFilename, file1);			 
		} catch (AwsServiceException | SdkClientException | IOException e) {
			log.error("Error while  saving image in bucket:{}",CommonsUtil.getErrorStacktrace(e));
			return false;
		}
		 return true;
	}

    @Override
    public CollegeImagesResponseDto update(CollegeImagesRequestDto requestDto) {
    	 try {
             return Optional.ofNullable(requestDto)
                     .map(collegeImagesDomainMapper::map)
                   //  .flatMap(this::validateUpdate)
                     .map(collegeImagesRepository::save)
                     .map(collegeImagesDomainMapper::map)
                     .orElseGet(CollegeImagesResponseDto::new);
         } catch (Exception ex) {
             throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
         }
    }

   
    
   /* private Optional<CollegeImagesEntity> validate(CollegeImagesEntity collegeImagesEntity) {
    	CollegeImagesEntity collegeEntityD = collegeImagesRepository.findByCollegeNCourse(collegeImagesEntity.getCollegeId(),collegeImagesEntity.getCourseId());
		if (collegeEntityD != null) {
			throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
		} else {
			return Optional.ofNullable(collegeImagesEntity);
		}
    }
    
    private Optional<CollegeImagesEntity> validateUpdate(CollegeImagesEntity collegeImagesEntity) {
    	CollegeImagesEntity collegeEntityD = collegeImagesRepository.findByCollegeNCourse(collegeImagesEntity.getCollegeId(),collegeImagesEntity.getCourseId());
		if (collegeEntityD != null && collegeEntityD.getCollegeCourseId() == collegeImagesEntity.getCollegeCourseId()) {
			return Optional.ofNullable(collegeImagesEntity);
		} else {
			throw new HMSException(ErrorCode.HMS0003, ErrorCode.HMS0003.getMessage());
		}
    }*/

	@Override
	public CollegeImagesResponseDto delete(Long id) {
		 try {
             return Optional.ofNullable(id)
            		 .map(collegeImagesRepository::getById)
            		 .flatMap(this::updateValues)
                     .map(collegeImagesRepository::save)
                     .map(collegeImagesDomainMapper::map)
                     .orElseGet(CollegeImagesResponseDto::new);
         } catch (Exception ex) {
             throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
         }
	}
	
	 private Optional<CollegeImagesEntity> updateValues(CollegeImagesEntity collegeEntity) {
		 collegeEntity.setActive(false);
		 return Optional.ofNullable(collegeEntity);
		 }

	@Override
	public List<CollegeImagesResponseDto> get() {
		try {
            return collegeImagesRepository.findAll().stream()
                    .map(collegeImagesDomainMapper::map).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new HMSException(ErrorCode.HMS0001, ErrorCode.HMS0001.getMessage());
        }
	}

	@Override
	public String saveFile(MultipartFile file) {
		
		   String originalFilename = file.getOriginalFilename();
	        while(true) {
	            try {
	                File file1 = convertMultiPartToFile(file);
	                PutObjectResult putObjectResult = s3.putObject(bucketName, originalFilename, file1);
	                return "Success";
	            }  catch (AwsServiceException | SdkClientException | IOException e) {
	    			log.error("Error while  saving image in bucket:{}",CommonsUtil.getErrorStacktrace(e));
	    		}
	        }
	}

	  private File convertMultiPartToFile(MultipartFile file ) throws IOException
	    {
	        File convFile = new File( file.getOriginalFilename() );
	        FileOutputStream fos = new FileOutputStream( convFile );
	        fos.write( file.getBytes() );
	        fos.close();
	        return convFile;
	    }	
}
