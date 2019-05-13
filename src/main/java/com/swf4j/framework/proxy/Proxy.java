/**
 * created by Zheng Jiateng on 2019/5/13.
 */
package com.swf4j.framework.proxy;

/**
 * 增强
 */
public interface Proxy {

    /**
     * 执行链式代理
     * 在实现类中会提供相应的横切逻辑，并调用proxyChain的doProxyChain方法，这会调用下一个Proxy对象的doProxy，从而链式传导下去，
     * 直到proxyIndex达到proxyList的上限为止
     *
     * 链式代理：将多个代理通过一条链子穿起来，一个个地去执行，执行顺序取决于添加到链上的先后顺序
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
