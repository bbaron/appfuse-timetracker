package com.bbaron.timetracker.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.Assert;

public class Utils {

    public static Map<String, String> toMap(Collection<?> c, String keyProperty, String valueProperty) {
        Assert.hasText(keyProperty);
        Assert.hasText(valueProperty);
        Assert.notNull(c);
        Map<String, String> result = new LinkedHashMap<String, String>();
        
        for (Object bean : c) {
            String key = null;
            String value = null;
            try {
                key = BeanUtils.getProperty(bean, keyProperty);
            } catch (Exception e) {
                throw new IllegalArgumentException(keyProperty + " is invalid", e);
            }
            try {
                value = BeanUtils.getProperty(bean, valueProperty);
            } catch (Exception e) {
                throw new IllegalArgumentException(valueProperty + " is invalid", e);
            }
            result.put(key, value);
        }
        
        return result;
    }
}
