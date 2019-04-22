package com.swf4j.framework.common;

/**
 * 常量，维护配置文件中相关的配置项名称
 */
public interface ConfigConstant {

    String CONFIG_FILE = "swf4j.properties";

    String JDBC_DRIVER = "swf4j.framework.jdbc.driver";
    String JDBC_URL = "swf4j.framework.jdbc.url";
    String JDBC_USERNAME = "swf4j.framework.jdbc.username";
    String JDBC_PASSWORD = "swf4j.framework.jdbc.password";

    // 整个应用的基础包名
    String APP_BASE_PACKAGE = "swf4j.framework.app.base_package";
    String APP_JSP_PATH = "swf4j.framework.app.jsp_path";
    String APP_ASSET_PATH = "swf4j.framework.app.asset_path";


}
