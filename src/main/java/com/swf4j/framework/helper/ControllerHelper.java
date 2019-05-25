/**
 * created by Zheng Jiateng on 2019/4/22.
 */
package com.swf4j.framework.helper;

import com.swf4j.framework.annotation.Action;
import com.swf4j.framework.bean.Handler;
import com.swf4j.framework.bean.Request;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 */
public final class ControllerHelper {

    /**
     * 用于存放请求对象和处理对象的映射关系 Action Map 由此实现RequestMapping的功能
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        // 获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            // 遍历这些Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    // 遍历此Controller类中的方法
                    for (Method method : methods) {
                        // 看此方法是否带有@Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 用正则表达式验证URL映射规则 eg: @Action("get:/customer_show")
                            if (mapping.matches("\\w+:/\\w+")) {
                                String[] strings = mapping.split(":");
                                if (ArrayUtils.isNotEmpty(strings) && strings.length == 2) {
                                    // 获取请求方法与请求路径
                                    String requestMethod = strings[0];
                                    String requestPath = strings[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取对应Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
