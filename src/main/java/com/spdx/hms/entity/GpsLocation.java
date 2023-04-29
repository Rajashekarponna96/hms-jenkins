package com.spdx.hms.entity;

import lombok.*;

import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GpsLocation {
	
   String lat;
   String lon;
}
