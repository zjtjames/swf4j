/**
 * created by Zheng Jiateng on 2019/4/17.
 */
package com.swf4j.framework.helper;

import com.swf4j.framework.ConfigConstant;
import com.swf4j.framework.util.PropsUtil;

/**
 * 读取用户填写的配置文件
 */
public final class ConfigHelper {

    /**
     * 获取JDBC驱动
     */
    public static String getJdbcDriver() {
        return PropsUtil.getProperty(ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取JDBC URL
     */
    public static String getJdbcUrl() {
        return PropsUtil.getProperty(ConfigConstant.JDBC_URL);
    }

    /**
     * 获取JDBC 用户名
     */
    public static String getJdbcUsername() {
        return PropsUtil.getProperty(ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取JDBC 密码
     */
    public static String getJdbcPassword() {
        return PropsUtil.getProperty(ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     */
    public static String getAppBasePackage() {
        return PropsUtil.getProperty(ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用JSP路径
     * 有默认值
     */
    public static String getAppJspPath() {
        return PropsUtil.getProperty(ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    /**
     * 获取应用静态资源路径
     * 有默认值
     */
    public static String getAppAssetPath() {
        return PropsUtil.getProperty(ConfigConstant.APP_ASSET_PATH, "/asset/");
    }


}
