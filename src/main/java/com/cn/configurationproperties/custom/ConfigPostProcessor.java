package com.cn.configurationproperties.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-24 13:49
 **/
@Component
public class ConfigPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Config configAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), Config.class);
        if(configAnnotation != null) {
            Map<String, String> configProperties = getConfigPropertiesFromFile(configAnnotation);
            bindBeanValue(bean, configProperties);
        }
        return null;
    }

    private Map<String, String> getConfigPropertiesFromFile(Config configAnnotation) {
        String prefix = configAnnotation.prefix();
        Properties properties = getClassNameFromResource("springbootstudy/configurationProperties/config.properties");
        Map<String, String> result = new HashMap<>(properties.size());
        Set<String> keys = properties.stringPropertyNames();
        List<String> keyList = new ArrayList<>(keys);
        for (String key : keyList) {
            //key为default.username，key就为username
            String realKey = key.substring(key.indexOf(prefix) + prefix.length() + 1);
            String value = properties.getProperty(key);
            result.put(realKey, value);
        }
        return result;
    }

    private Properties getClassNameFromResource(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = ConfigPostProcessor.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void bindBeanValue(Object bean, Map<String, String> configProperties) {
        if(configProperties.size() > 0) {
            configProperties.forEach((key, value) -> {
                setFieldValueByFieldName(key, bean, value);
            });
        }
    }

    private void setFieldValueByFieldName(String fieldName, Object bean, String value) {
        Class<?> clazz = bean.getClass();
        if(checkFieldExists(fieldName, clazz)) {
            try {
                Field declaredField = clazz.getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Class<?> type = declaredField.getType();
                if(type.equals(String.class)) {
                    declaredField.set(bean, value);
                }else {
                    declaredField.set(bean, Integer.valueOf(value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkFieldExists(String fieldName, Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if(field.getName().equalsIgnoreCase(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
