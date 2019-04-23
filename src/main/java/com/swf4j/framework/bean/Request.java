/**
 * created by Zheng Jiateng on 2019/4/22.
 */
package com.swf4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息 @Action注解中的成员信息 类比Spring中@RequestMapping(value = "logout.do",method = RequestMethod.POST)
 */
public class Request {

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    // 通过对象的成员变量计算hash
    // 重写Request的hashCode()和equals()是为了使得只要有相同的requestMethod和requestPath，就能从ACTION_MAP中取得同一个Handler
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }
}
