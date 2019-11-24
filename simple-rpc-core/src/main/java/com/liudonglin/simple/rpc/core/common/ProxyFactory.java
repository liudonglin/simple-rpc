package com.liudonglin.simple.rpc.core.common;

import com.liudonglin.simple.rpc.core.protocol.http.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                HttpClient httpClient = new HttpClient();
                RpcInvocation invocation = new RpcInvocation(interfaceClass.getName(),method.getName(),method.getParameterTypes(),args);
                Object result = httpClient.send("localhost",8080,invocation);
                return result;
            }
        });
    }

}
