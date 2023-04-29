package com.spdx.hms.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonsUtil {
	
	public static String getErrorStacktrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		return sw.toString();

	}
	
	public static String getAfterTime(LocalDateTime df) {
		Instant instant = df.atZone(ZoneId.systemDefault()).toInstant();
		//instant= instant.minusSeconds(80);
		return instant.getEpochSecond()+"000";
	}
	
	public static String  getBeforeTime(LocalDateTime df) {
		Instant instant = df.atZone(ZoneId.systemDefault()).toInstant();
		instant= instant.plusSeconds(120);
		return instant.getEpochSecond()+"000";
	}
	
	public static LocalDateTime getDate(String date) {
		DateTimeFormatter zonedFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return LocalDateTime.parse(date, zonedFormatter);
	}
	
	public static Timestamp getTimestamp(String date) {
		return Timestamp.valueOf(getDate(date));
	}
	
	public static long difference( LocalDateTime fromDate,LocalDateTime toDate) {
		return fromDate.until(toDate, ChronoUnit.MINUTES);
		
	}
	
	public static long differenceSeconds( LocalDateTime fromDate,LocalDateTime toDate) {
		return fromDate.until(toDate, ChronoUnit.SECONDS);
		
	}
	
	public static boolean isGreater(Timestamp from, Timestamp to) {
		if(from.compareTo(to) >= 0) {
			return false;
		}
		return true;
	}
	
	
	
	public static String getCurrentDate() {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");  
	   return formatter.format(date);  
	}
	
	public static String generateRandomPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String newChars = chars.concat(specialChar);
        List<Character> pwdChars = newChars.chars()
        	      .mapToObj(c -> (char) c)
        	      .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
        	      .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
        	      .toString();
        return RandomStringUtils.random(len, password);
    }
	
	public static String getCurrentHour() {
		int hour =LocalDateTime.now().getHour();
		return hour+"";
	}
	
	public static String generateOTP(int otpLength) {
		SplittableRandom splittableRandom = new SplittableRandom();
		StringBuilder strb=new StringBuilder();
		for(int i=0;i<otpLength;i++) {
			strb.append(splittableRandom.nextInt(0, 10));
			
		}
		return strb.toString();
		
	}
	
	private  Long diffInSeconds(Date previousDate) {
		Date currentDate = new Date();
		long duration = currentDate.getTime() - previousDate.getTime();

		return TimeUnit.MILLISECONDS.toSeconds(duration);

	}
	
	public static void main(String str[]) {
		String fromDate="04-04-2022 23:00";
		String toDate="04-04-2022 18:00";
		DateTimeFormatter zonedFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime df=LocalDateTime.parse(fromDate, zonedFormatter);
		LocalDateTime df2=LocalDateTime.parse(toDate, zonedFormatter);
		System.out.println(LocalDateTime.now()+"--"+LocalDateTime.now().getHour());
		System.out.println(df.getHour());
		df2 =df2.plusMinutes(20);
		System.out.println(df+".."+df2);
		Long l=df.until(df2, ChronoUnit.MINUTES);
		System.out.println(l);
		Timestamp time=getTimestamp(fromDate);
		System.out.println(time);
		time.setHours(time.getHours()+1);
		System.out.println(time);
		time.setHours(time.getHours()+1);
		System.out.println(time);
		time.setHours(time.getHours()+1);
		System.out.println(time);
		//Timestamp toDate=time;
		
		
		
	}

}
