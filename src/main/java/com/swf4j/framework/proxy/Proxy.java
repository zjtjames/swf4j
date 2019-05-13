/**
 * created by Zheng Jiateng on 2019/5/13.
 */
package com.swf4j.framework.proxy;

/**
 * 代理接口
 */
public interface Proxy {

    /**
     * 执行链式代理
     *
     * 链式代理：将多个代理通过一条链子穿起来，一个个地去执行，执行顺序取决于添加到链上的先后顺序
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
