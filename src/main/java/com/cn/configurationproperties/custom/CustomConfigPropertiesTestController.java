package com.cn.configurationproperties.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-24 14:32
 **/
@RestController
@RequestMapping(value = "/customProperties")
public class CustomConfigPropertiesTestController {
    @Autowired
    private TestDataSource testDataSource;

    @GetMapping(value = "/getDataSource")
    public TestDataSource getDataSource() {
        return testDataSource;
    }
}
