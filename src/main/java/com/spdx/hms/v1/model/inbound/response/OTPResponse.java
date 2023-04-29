package com.spdx.hms.v1.model.inbound.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPResponse {
    private UUID tokenId;
    private String mobileNumber;
    private String status;
    private String otp;

}
