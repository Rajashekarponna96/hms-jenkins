     package com.spdx.hms.client;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestClient {

	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private RestTemplate restTemplate;

	
	private HttpHeaders headers;


	private static final String BEARER = "Bearer ";



	public HttpHeaders createHttpHeaders(String userId, String password) throws Exception {
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON, MediaType.ALL }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	public static void main(String args[]) throws IOException {

		/*String pageToken = "HuzCSH78h2c+Kni5MEtSunh2UkpHeHhhNERDYSqXz0+HLGyG+xdPlN5cy2lVnbrM+hEn+J59zAp/3HkLrn4Twtry2SG6gsxzy5hAvCtjzSi0hUmRlyXVSt2vDAN558lN98tdCIdYn5jVUg==";

		UriComponents builder = null;
		builder = UriComponentsBuilder.fromHttpUrl("http://dfd.com").queryParam("pageToken", pageToken).encode()
				.build();
		System.out.println(builder.toUriString());
		builder = UriComponentsBuilder.fromHttpUrl("http://dfd.com").queryParam("pageToken", pageToken).build();
		System.out.println(builder.toUri());
		System.out.println(encodeValue(pageToken));
		System.out.println(UriUtils.encodeQueryParam(pageToken, "UTF-8"));*/
		String msg="Your OTP for mobile verification is {1234} regards ONEHMT";
		
		UriComponents builder = null;
		builder = UriComponentsBuilder.fromHttpUrl("https://www.smsstriker.com/API/sms.php")
				.queryParam("username", "Servicetouch")
				.queryParam("password", "@nt122342")
				.queryParam("from", "8978469444")
				.queryParam("to", "9912095559")
				.queryParam("msg", msg)
				.queryParam("type", "1")
				.queryParam("template_id", "1707161908265734005")
				.queryParam("sender", "ONEHMT")
				.encode()
				.build();
		System.out.println(builder.toUriString());
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		//.url("https://www.smsstriker.com/API/sms.php?username=Servicetouch&password=@nt122342&from=8978469444&to=9912095559&msg="+msg+"&type=1&template_id=1707161908265734005&sender=ONEHMT")
		.url(builder.encode().toUriString())
				.get()
		.build();
		Response response = client.newCall(request).execute();
		System.out.println(response);
	}
}
