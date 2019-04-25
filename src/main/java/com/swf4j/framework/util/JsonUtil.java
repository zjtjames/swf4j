/**
 * created by Zheng Jiateng on 2019/4/25.
 */
package com.swf4j.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类：处理JSON和POJO之间的转换，基于Jackson实现
 */
public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转为JSON
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("convert pojo to json failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON转为POJP
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            logger.error("convert json to pojo failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
