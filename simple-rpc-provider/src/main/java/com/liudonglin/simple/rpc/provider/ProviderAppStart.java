package com.liudonglin.simple.rpc.provider;

import com.liudonglin.simple.rpc.api.GreetingService;
import com.liudonglin.simple.rpc.core.common.URL;
import com.liudonglin.simple.rpc.core.protocol.http.HttpServer;
import com.liudonglin.simple.rpc.core.register.LocalRegister;
import com.liudonglin.simple.rpc.core.register.RemoteMapRegister;
import com.liudonglin.simple.rpc.provider.service.GreetingServiceImpl;

import java.rmi.Remote;

public class ProviderAppStart {

    public static void main(String[] args) {
        //注册服务
        URL url = new URL("http","localhost",8080);
        RemoteMapRegister.regist(GreetingService.class.getName(),url);
        LocalRegister.regist(GreetingService.class.getName(), GreetingServiceImpl.class);

        //暴露服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url);
    }
}
