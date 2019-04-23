/**
 * created by Zheng Jiateng on 2019/4/23.
 */
package com.swf4j.framework.bean;

/**
 * 数据对象
 * 若Action方法返回值是Data类型的数据对象，则返回一个JSON数据
 * 返回的Data类型的数据封装了一个Object类型的模型数据，框架将会将该对象写入HttpServletResponse对象中，从而直接输入值浏览器。
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
