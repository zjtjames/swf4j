/**
 * created by Zheng Jiateng on 2019/4/23.
 */
package com.swf4j.framework.bean;

import java.util.Map;

/**
 * 请求参数对象 url中?后面的部分即为参数
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数值
     */


    /**
     * 获取所有字段信息
     */
    public Map<String, Object> getParamMap() {
        return this.paramMap;
    }

}
