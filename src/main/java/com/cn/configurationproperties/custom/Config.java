package com.cn.configurationproperties.custom;

import java.lang.annotation.*;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-24 13:45
 **/
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
    /**
     * 配置属性的前缀
     * @return
     */
    String prefix();
}
