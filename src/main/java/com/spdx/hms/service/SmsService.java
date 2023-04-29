package com.spdx.hms.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsService {

	@Value("${spring.mail.from}")
	private String emailFrom;

	public void generateOTPSms(String mobileNumber,String otp) {
		String msg = "Your OTP for mobile verification is {"+otp+"} regards ONEHMT";

		UriComponents builder = null;
		builder = UriComponentsBuilder.fromHttpUrl("https://www.smsstriker.com/API/sms.php")
				.queryParam("username", "Servicetouch").queryParam("password", "@nt122342")
				.queryParam("from", "ONEHMT").queryParam("to", mobileNumber).queryParam("msg", msg)
				.queryParam("type", "1").queryParam("template_id", "1707161908265734005")
				//.queryParam("sender", "ONEHMT")
				.encode().build();
		System.out.println(builder.toUriString());
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				// .url("https://www.smsstriker.com/API/sms.php?username=Servicetouch&password=@nt122342&from=8978469444&to=9912095559&msg="+msg+"&type=1&template_id=1707161908265734005&sender=ONEHMT")
				.url(builder.encode().toUriString()).get().build();
		Response response =null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(response);
	}

}
