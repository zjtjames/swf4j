/**
 * created by Zheng Jiateng on 2019/4/17.
 */
package com.swf4j.framework.util;

import com.swf4j.framework.ConfigConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * 因为是工具类，所以实例变量和方法全是静态的
 */
public final class PropsUtil {

    private static Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    private static Properties props;

    static{
        String fileName = ConfigConstant.CONFIG_FILE;
        props = new Properties();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(PropsUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            logger.error("配置文件读取异常", e);
        }
    }

    /**
     * 获取属性（默认值为null）
     */
    public static String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    /**
     * 获取属性（可指定默认值）
     */
    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value.trim();
    }

}
