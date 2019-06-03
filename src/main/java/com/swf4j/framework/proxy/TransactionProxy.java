/**
 * created by Zheng Jiateng on 2019/6/2.
 */
package com.swf4j.framework.proxy;

import com.swf4j.framework.annotation.Transaction;
import com.swf4j.framework.helper.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


/**
 * 事务代理切面类
 * 在doProxy方法中完成事务控制的相关逻辑
 */
public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    // 一个标志，保证同一线程中事务控制相关逻辑只会执行一次
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){ // 匿名内部类
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    @Transaction
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        // @Transaction是注解在方法上的 所以AopHelper先抓所有的Service类，再在增强类内部判断此方法是否要进行事务控制
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Exception e) {
                // 出现异常则回滚事务
                DatabaseHelper.rollbackTransaction();
                LOGGER.debug("rollback transaction");
                throw e;
            } finally {
                FLAG_HOLDER.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> cls = TransactionProxy.class;
        Method method = cls.getMethod("doProxy", ProxyChain.class);
        System.out.println(cls.isAnnotationPresent(Transaction.class));
        System.out.println(method.isAnnotationPresent(Transaction.class));
    }
}
