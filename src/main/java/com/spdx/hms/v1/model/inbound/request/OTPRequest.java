package com.spdx.hms.v1.model.inbound.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPRequest {
    private String tokenId;
    private String mobileNumber;
    private String status;
    private String otp;

}
