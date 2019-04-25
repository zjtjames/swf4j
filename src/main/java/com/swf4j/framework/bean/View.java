/**
 * created by Zheng Jiateng on 2019/4/23.
 */
package com.swf4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图对象
 * 若Action方法返回值是View类型的视图对象，则返回一个JSP
 * 由于视图中时可以包含模型数据的，因此在View中包括了视图路径和该视图中所需的模型数据，该模型数据是一个Map类型的键值对，可以在视图中根据
 * 模型的键名获取键值
 */
public class View {

    /**
     * 视图(jsp)路径
     */
    private String path;

    /**
     * 模型数据：用于HttpServlet中的req.setAttribute("customerList", customerList);
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }


    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return this.path;
    }

    public Map<String, Object> getModel() {
        return this.model;
    }


}
