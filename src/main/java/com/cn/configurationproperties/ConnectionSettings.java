package com.cn.configurationproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-25 17:23
 **/
@Component
@ConfigurationProperties(prefix = "connection")
@Data
public class ConnectionSettings {
    private String username;
    private String password;
    private String remoteAddress;
}
