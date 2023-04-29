package com.spdx.hms.v1.model.inbound.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private boolean resetPassword;
    private String userId;
    private String role;
    private String status;
    private String message;
    private String userName;
    private String token;

}
