package com.liudonglin.simple.rpc.core.protocol.http;

import com.liudonglin.simple.rpc.core.common.RpcInvocation;
import com.liudonglin.simple.rpc.core.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpHandler {

    public void handle(HttpServletRequest req, HttpServletResponse res) {


        if (!req.getMethod().equalsIgnoreCase("POST")) {
            res.setStatus(500);
        } else {
            try {
                InputStream inputStream = req.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                RpcInvocation invocation = (RpcInvocation) ois.readObject();

                String interfaceName = invocation.getInterfaceName();
                Class implClass = LocalRegister.get(interfaceName);
                Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParameterTypes());

                Object result = method.invoke(implClass.newInstance(),invocation.getArguments());

                OutputStream outputStream = res.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);

                oos.writeObject(result);
                oos.flush();
                oos.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
