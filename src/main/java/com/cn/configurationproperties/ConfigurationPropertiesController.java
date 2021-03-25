package com.cn.configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-25 17:39
 **/
@RestController
@RequestMapping(value = "/configurationProperties")
public class ConfigurationPropertiesController {

    @Autowired
    private ConnectionSettings connectionSettings;

    @GetMapping(value = "/test")
    public ConnectionSettings get() {
        return connectionSettings;
    }
}
