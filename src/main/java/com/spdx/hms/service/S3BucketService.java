package com.spdx.hms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;

@Service
@Slf4j
public class S3BucketService {
	
	@Value("${aws.bucket.arn}")
	private String awsArn;	
	
	public S3Client getS3BucketConnection() {
		try {
			S3Client s3Client = S3Client.builder().region(Region.AP_SOUTH_1)
					.credentialsProvider(StsAssumeRoleCredentialsProvider.builder()
							.refreshRequest(
									() -> AssumeRoleRequest.builder().roleArn(awsArn).roleSessionName("test").build())
							.stsClient(StsClient.builder().region(Region.AP_SOUTH_1).build()).build())
					.build();

			log.info("service name:" + s3Client.serviceName());
			
			return s3Client;
		} catch (Exception ex) {

		}
		return null;
	}

}
