package com.spdx.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BucketFileInfo {

	private long warehouseId;
	private String fileName;
	private String fileSize;
	private String lastModified;
}
