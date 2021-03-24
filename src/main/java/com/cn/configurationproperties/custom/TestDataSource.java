package com.cn.configurationproperties.custom;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-24 13:46
 **/
@Data
@Component
@Config(prefix = "default")
public class TestDataSource {
    private String username;
    private String password;
    private int maxActiveCount;
}
