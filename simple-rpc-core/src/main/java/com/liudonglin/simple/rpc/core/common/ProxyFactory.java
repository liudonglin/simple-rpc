package com.liudonglin.simple.rpc.core.common;

import com.liudonglin.simple.rpc.core.protocol.http.HttpClient;
import com.liudonglin.simple.rpc.core.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                HttpClient httpClient = new HttpClient();
                RpcInvocation invocation = new RpcInvocation(interfaceClass.getName(),method.getName(),method.getParameterTypes(),args);

                List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());
                URL url = urls.get(0);

                Object result = httpClient.send(url.getHost(),url.getPort(),invocation);
                return result;
            }
        });
    }

}
