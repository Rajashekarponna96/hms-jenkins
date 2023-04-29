package com.spdx.hms.v1.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("com.spdx.hms")
public class HMSProperties {
    Boolean enable;
}
